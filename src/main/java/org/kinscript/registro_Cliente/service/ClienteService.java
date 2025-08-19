package org.kinscript.registro_Cliente.service;

import org.kinscript.registro_Cliente.entity.Cliente;

//Inyectar dependencia

//componente de SpringBoot para crear aplicaciones
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ClienteService implements IClienteService {

    @Override
    public List<Cliente> listarCliente() {
        return List.of();
    }

    @Override
    public Cliente buscarClienteporId(Integer codigo) {
        return null;
    }

    @Override
    public void guardarCliente(Cliente cliente) {

    }

    @Override
    public void eliminarCliente(Cliente cliente) {

    }
}
