package infraestructure.repositories;

import java.util.ArrayList;
import java.util.List;
import entities.Producto;
import usecases.ports.ProductoRepository;

public class InMemoryProductoRepository implements ProductoRepository {
    private final List<Producto> productos = new ArrayList<>();

    @Override
    public void save(Producto producto) {
        productos.add(producto);
    }

    @Override
    public Producto findById(string id) {
        for (Producto producto : productos) {
            if (producto.getId().equals(id)) {
                return producto;
            }
        }
        return null;
    }

    @Override
    public List<Producto> findAll() {
        return new ArrayList<>(productos);
    }
}