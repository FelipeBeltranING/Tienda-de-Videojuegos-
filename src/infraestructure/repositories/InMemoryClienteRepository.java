package infraestructure.repositories;

import java.util.ArrayList;
import java.util.List;
import entities.Cliente;
import usecases.ports.ClienteRepository;

public class InMemoryClienteRepository implements ClienteRepository {
    private final List<Cliente> clientes = new ArrayList<>();

    @Override
    public void save(Cliente cliente) {
        clientes.add(cliente);
    }

    @Override
    public Cliente findById(String id) {
        for (Cliente cliente : clientes) {
            if (cliente.getId().equals(id)) {
                return cliente;
            }
        }
        return null;
    }

    @Override
    public List<Cliente> findAll() {
        return new ArrayList<>(clientes);
    }
}