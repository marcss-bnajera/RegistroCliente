package org.kinscript.registro_Cliente.service;

import org.kinscript.registro_Cliente.entity.Cliente;

//Inyectar dependencia

//componente de SpringBoot para crear aplicaciones
import org.kinscript.registro_Cliente.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ClienteService implements IClienteService {
    //Inyeccion de dependencias
    @Autowired
    private ClienteRepository clienteRepository;




    @Override
    public List<Cliente> listarCliente() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes;
    }

    @Override
    public Cliente buscarClienteporId(Integer codigo) {
        Cliente cliente = clienteRepository.findById(codigo).orElse(null);
        return cliente;
    }

    @Override
    public void guardarCliente(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    @Override
    public void eliminarCliente(Cliente cliente) {
        clienteRepository.delete(cliente);
    }
}
