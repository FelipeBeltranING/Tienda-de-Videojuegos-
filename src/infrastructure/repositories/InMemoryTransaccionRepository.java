package infrastructure.repositories;

import java.util.ArrayList;
import java.util.List;
import entities.Transaccion;
import usecases.ports.TransaccionRepository;

public class InMemoryTransaccionRepository implements TransaccionRepository {
    private final List<Transaccion> transaccions = new ArrayList<>();

    @Override
    public void save(Transaccion transaccion) {
        transaccions.add(transaccion);
    }

    @Override
    public Transaccion findById(String id) {
        for (Transaccion transaccion : transaccions) {
            if (transaccion.getId().equals(id)) {
                return transaccion;
            }
        }
        return null;
    }

    @Override
    public List<Transaccion> findAll() {
        return new ArrayList<>(transaccions);
    }
}