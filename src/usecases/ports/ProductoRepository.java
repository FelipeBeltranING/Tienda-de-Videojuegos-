package usecases.ports;

import java.util.List;
import entities.Producto;

public interface ProductoRepository {
    void save(Producto producto);
    Producto findById(String id);
    List<Producto> findAll();
}
