package usecases.ports;

import java.util.List;

import entities.Transaccion;

public interface TransaccionRepository {
    void save(Transaccion transaccion);
    Transaccion findById(String id);
    List<Transaccion> findAll();
}

