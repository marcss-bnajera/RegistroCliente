package org.kinscript.registro_Cliente.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import org.kinscript.registro_Cliente.entity.Cliente;
import org.kinscript.registro_Cliente.service.IClienteService;
import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.List;

//Componente generico
@Component
//alcance de tipo VIEW
@ViewScoped
//Getter y Setter de lombok
@Data
public class indexController {
    @Autowired
    IClienteService clienteService;
    private List<Cliente> clientes;
    private Cliente clienteSeleccionado;
    private static Logger logger = LoggerFactory.getLogger(indexController.class);

    @PostConstruct
    public void init() {
        cargarDatos();
    }

    public void cargarDatos() {
        this.clientes = this.clienteService.listarCliente();
        this.clientes.forEach(cliente -> logger.info(cliente.toString()));
    }

    public void agregarCliente() {
        this.clienteSeleccionado = new Cliente();
    }

    public void guardaCliente() {
        logger.info("Cliente a guardar: " +this.clienteSeleccionado);
        //agregar
        if (this.clienteSeleccionado.getCodigoCliente() == null) {
            this.clienteService.guardarCliente(this.clienteSeleccionado);
            this.clientes.add(this.clienteSeleccionado);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cliente agregado"));
        }
        //modificar
        else {
            this.clienteService.guardarCliente(this.clienteSeleccionado);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cliente actualizado"));
        }
        //ocultar la ventana modal
        PrimeFaces.current().executeScript("PF('ventanaModalCliente').hide");
        //Actualizar tabla utilizando tecnolofia incorporada - AJAX -
        PrimeFaces.current().ajax().update("formulario-clientes:mensaje-emergente", "formulario-clientes:table-clientes");
        //Limpiar el objeto cliente seleccionado
        this.clienteSeleccionado = null;
    }

    public void eliminarCliente() {
        logger.info("Cliente a eliminar: " +this.clienteSeleccionado);
        this.clienteService.eliminarCliente(this.clienteSeleccionado);
        //Eliminar el registro de la listade clientes
        this.clientes.remove(this.clienteSeleccionado);
        //Reiniciar el objeto cliente seleccionado
        this.clienteSeleccionado = null;
        //confirmar accion
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cliente eliminado"));
        PrimeFaces.current().ajax().update("formulario-clientes:mensaje-emergente","formulario-clientes:table-clientes");

    }

}

