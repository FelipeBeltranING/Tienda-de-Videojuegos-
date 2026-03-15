package adapters.console;

import java.util.Scanner;
import entities.*;
import infrastructure.repositories.InMemoryClienteRepository;
import infrastructure.repositories.InMemoryProductoRepository;
import infrastructure.repositories.InMemoryTransaccionRepository;
import infrastructure.services.IdGenerator;
import usecases.dto.OperationResult;
import usecases.ports.ClienteRepository;
import usecases.ports.IdGeneratorRepository;
import usecases.ports.ProductoRepository;
import usecases.ports.TransaccionRepository;
import usecases.services.*;


public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        CalculadoraPrecio calculadoraPrecio = new CalculadoraPrecio();

        IdGeneratorRepository idGeneratorRepository = new IdGenerator();
        ClienteRepository clienteRepository = new InMemoryClienteRepository();
        ProductoRepository productoRepository = new InMemoryProductoRepository();
        TransaccionRepository transaccionRepository = new InMemoryTransaccionRepository();


        RegistrarClienteUseCase registrarCliente = new RegistrarClienteUseCase(clienteRepository,idGeneratorRepository);
        RegistrarProductoUseCase registrarProducto = new RegistrarProductoUseCase(productoRepository,idGeneratorRepository);
        RegistrarTransaccionUseCase registrarTransaccion = new RegistrarTransaccionUseCase(transaccionRepository,idGeneratorRepository);
        VenderUseCase venderUseCase = new VenderUseCase(idGeneratorRepository,productoRepository,clienteRepository,transaccionRepository);

        TiendaVideojuego tienda = new TiendaVideojuego("GameStore",clienteRepository,productoRepository,transaccionRepository,registrarCliente,registrarProducto,registrarTransaccion,venderUseCase);

        registrarCliente.ejecutar("Lucia","Lucia@mail.com");
        registrarCliente.ejecutar("Felipe","Felipe@mail.com");

        tienda.registrarConsola("Z BOX",239.99,true,2,"MacroSoft");
        tienda.registrarVideojuego("God of peace",19.99, true,3,"PolyStation 5","Accion");

        int op;
        do {
            System.out.println("\n=== " + tienda.getNombre() + " ===");
            System.out.println("1) Listar productos");
            System.out.println("2) Listar clientes");
            System.out.println("3) Listar transacciones");
            System.out.println("4) Vender producto");
            System.out.println("0) Salir");
            System.out.print("Opción: ");
            op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1 -> {
                    System.out.println("--- Lista de Productos ---");
                    tienda.listarProductos().forEach(p -> System.out.println(p));
                }
                case 2 -> {
                    System.out.println("--- Lista de Clientes ---");
                    tienda.listarClientes().forEach(c -> System.out.println(c));
                }
                case 3 -> {
                    System.out.println("--- Lista de Transacciones ---");
                    tienda.listarTransacciones().forEach(t -> System.out.println(t));
                }
                case 4 -> {
                    System.out.print("ID Cliente: ");
                    String idCliente = sc.nextLine();
                    System.out.print("ID Producto: ");
                    String idProducto = sc.nextLine();
                    OperationResult res = tienda.realizarVenta(idCliente, idProducto);
                    // IMPORTANTE: Imprime el resultado de la venta
                    System.out.println(res.getMessage());
                }
            }
        } while (op != 0);

        sc.close();
    }

}