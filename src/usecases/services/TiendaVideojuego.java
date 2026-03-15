package usecases.services;
import entities.*;
import infrastructure.repositories.InMemoryClienteRepository;
import infrastructure.repositories.InMemoryProductoRepository;
import infrastructure.repositories.InMemoryTransaccionRepository;
import usecases.dto.OperationResult;
import usecases.ports.ClienteRepository;
import usecases.ports.IdGeneratorRepository;
import usecases.ports.ProductoRepository;
import usecases.ports.TransaccionRepository;


import java.util.ArrayList;
import java.util.List;

public class  TiendaVideojuego {

    private String nombre;
    private final ClienteRepository clienteRepository;
    private final ProductoRepository productoRepository;
    private final TransaccionRepository transaccionRepository;

    private final RegistrarClienteUseCase registrarClienteUseCase;
    private final RegistrarProductoUseCase registrarProductoUseCase;
    private final RegistrarTransaccionUseCase registrarTransaccionUseCase;
    private final VenderUseCase venderUseCase;
    private final CalculadoraPrecio calculadoraPrecio;

    public TiendaVideojuego(String nombre, ClienteRepository clienteRepository, ProductoRepository productoRepository, TransaccionRepository transaccionRepository, RegistrarClienteUseCase registrarClienteUseCase, RegistrarProductoUseCase registrarProductoUseCase, RegistrarTransaccionUseCase registrarTransaccionUseCase, VenderUseCase venderUseCase) {
        this.nombre = nombre;
        this.clienteRepository = clienteRepository;
        this.productoRepository = productoRepository;
        this.transaccionRepository = transaccionRepository;
        this.registrarClienteUseCase = registrarClienteUseCase;
        this.registrarProductoUseCase = registrarProductoUseCase;
        this.registrarTransaccionUseCase = registrarTransaccionUseCase;
        this.calculadoraPrecio = new CalculadoraPrecio();
        this.venderUseCase = venderUseCase;
    }

    public String getNombre() {
        return nombre;
    }

    public void registrarConsola(String titulo, double precio, boolean disp, int unidades, String marca) {
        registrarProductoUseCase.ejecutar(titulo, precio, disp, unidades, marca);
    }

    public void registrarVideojuego(String titulo, double precio, boolean disp, int unidades, String plat, String gen) {
        registrarProductoUseCase.ejecutar(titulo, precio, disp, unidades, plat, gen);
    }

    public void registrarCliente(String nombre, String email) {
        registrarClienteUseCase.ejecutar(nombre,email);
    }

    public void registrarTransaccion(Cliente cliente, Producto producto, DetalleTransaccion detalleTransaccion) {
        registrarTransaccionUseCase.ejecutar(cliente,producto,detalleTransaccion);
    }

    public OperationResult realizarVenta(String idCliente, String idProducto) {
        return venderUseCase.ejecutar(idCliente, idProducto);
    }

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    public List<Transaccion> listarTransacciones() {
        return transaccionRepository.findAll();
    }

}

