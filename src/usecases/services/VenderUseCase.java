package usecases.services;

import entities.Cliente;
import entities.DetalleTransaccion;
import entities.Producto;
import entities.Transaccion;
import usecases.dto.OperationResult;
import usecases.ports.ClienteRepository;
import usecases.ports.IdGeneratorRepository;
import usecases.ports.ProductoRepository;
import usecases.ports.TransaccionRepository;

public class VenderUseCase {

    private final IdGeneratorRepository idGeneratorRepository;
    private final ProductoRepository productoRepository;
    private final ClienteRepository clienteRepository;
    private final TransaccionRepository transaccionRepository;
    private final CalculadoraPrecio calculadoraPrecio;

    public VenderUseCase(IdGeneratorRepository idGeneratorRepository, ProductoRepository productoRepository, ClienteRepository clienteRepository, TransaccionRepository transaccionRepository) {
        this.idGeneratorRepository = idGeneratorRepository;
        this.productoRepository = productoRepository;
        this.clienteRepository = clienteRepository;
        this.transaccionRepository = transaccionRepository;
        this.calculadoraPrecio = new CalculadoraPrecio();
    }

    public OperationResult ejecutar(String idCliente, String idProducto) {

        Cliente cliente = clienteRepository.findById(idCliente);
        if (cliente == null) {
            return OperationResult.fail("Usuario no encontrado.");
        }

        Producto producto = productoRepository.findById(idProducto);
        if (producto == null) {
            return OperationResult.fail("Producto no encontrado.");
        }

        if (!producto.estaDisponible()) {
            return OperationResult.fail("Producto no disponible.");
        }

        DetalleTransaccion detalle = new DetalleTransaccion(calculadoraPrecio.calcularPrecioVenta(producto.getPrecioBase(),Reglas.iva),producto.getPrecioBase());

        Transaccion transaccion = new Transaccion(idGeneratorRepository.nextId('T'), cliente, producto, detalle);

        transaccionRepository.save(transaccion);
        producto.vender();

        return OperationResult.ok(
                "Venta realizada -> " + producto.getTitulo() + " (ID: " + producto.getId() + ")\n" +
                        "ID Transacción: " + transaccion.getId() + "\n" +
                        "Subtotal: " + transaccion.getDetalleTransaccion().getSubtotal() + " | IVA: " + Reglas.iva + "\n" +
                        "Costo total: " + transaccion.getDetalleTransaccion().getTotal() + "$"
        );
    }
}
