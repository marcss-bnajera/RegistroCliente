package org.kinscript.registro_Cliente.service;
import org.kinscript.registro_Cliente.entity.Cliente;

import java.util.List;

public interface IClienteService {
    public List<Cliente> listarCliente();
    public Cliente buscarClienteporId(Integer codigo);
    public void guardarCliente(Cliente cliente);
    public void eliminarCliente(Cliente cliente);
}
