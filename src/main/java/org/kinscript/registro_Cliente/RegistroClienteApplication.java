package org.kinscript.registro_Cliente;

import org.kinscript.registro_Cliente.entity.Cliente;
import org.kinscript.registro_Cliente.service.IClienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;


//@SpringBootApplication
public class RegistroClienteApplication implements CommandLineRunner {

	//Inyeccion de dependencias
	@Autowired
	private IClienteService clienteService;

	//profesionalizar nuestro SOUT como un Loger
	private static final Logger logger = LoggerFactory.getLogger(RegistroClienteApplication.class);
	//Agregar un String para salto de linea
	String salto = System.lineSeparator();

	public static void main(String[] args) {
		//antes de iniciar
		logger.info("Iniciando la aplicacion");
		SpringApplication.run(RegistroClienteApplication.class, args);
		//al finalizar
		logger.info("Aplicacion Finalizada");

	}

	@Override
	public void run(String... args) throws Exception {
		registroClientesApp();
	}

	private void registroClientesApp() {
		logger.info("=====Bienvenido a la aplicacion de Registro de Cliente=====");
		var salir = false;
		var consola = new Scanner(System.in);
		while (!salir) {
			var opcion = mostrarMenu(consola);
			salir = ejecutarOpciones(consola, opcion);
			logger.info(salto);
		}
	}

	private int mostrarMenu(Scanner consola) {
		logger.info("""
				***==Aplicacion==***
				1. Listar Clientes
				2. Buscar Clientes
				3. Agregar Clientes
				4. Modificar Cliente
				5. Eliminar Cliente
				6. Salir
				""");
		var opcion = Integer.parseInt(consola.nextLine());
		return opcion;
	}

	private boolean ejecutarOpciones(Scanner consola, int opcion) {
		var salir = false;
		switch (opcion) {
			case 1 -> {
				logger.info(salto+"***==Lista Clientes==***"+salto);
				List<Cliente> clientes = clienteService.listarCliente();
				clientes.forEach(cliente -> logger.info(cliente.toString()+salto));
			}
			case 2 -> {
				logger.info("***==Buscar Cliente por su ID==***"+salto);
				var codigo = Integer.parseInt(consola.nextLine());
				Cliente cliente = clienteService.buscarClienteporId(codigo);
				if (cliente != null) {
					logger.info("Cliente encontrado: " + cliente +salto);
				} else {
					logger.info("Cliente no encontrado: " +cliente+salto);
				}
			}
			case 3 -> {
				logger.info("***==Agregar Cliente==***" +salto);
				logger.info("Ingrese el nombre del cliente: ");
				var nombre = consola.nextLine();
				logger.info("Ingrese el apellido del cliente: ");
				var apellido = consola.nextLine();
				logger.info("Ingrese el telefono del cliente: ");
				var telefono = consola.nextLine();
				logger.info("Ingrese el correo del cliente: ");
				var correo = consola.nextLine();
				logger.info("Ingrese el genero del cliente: ");
				var genero = consola.nextLine();
				logger.info("Ingrese la edad del cliente: ");
				var edad = Integer.parseInt(consola.nextLine());
				var cliente = new Cliente();
				cliente.setNombre(nombre);
				cliente.setApellido(apellido);
				cliente.setTelefono(telefono);
				cliente.setCorreo(correo);
				cliente.setGenero(genero);
				cliente.setEdad(edad);
				clienteService.guardarCliente(cliente);
				logger.info("Cliente agregado: " + cliente+salto);
			}
			case 4 -> {
				logger.info("***==Modificar Cliente==***" +salto);
				//Buscar por codigo
				logger.info("Agregue el codigo del cliente a modificar: ");
				var codigo = Integer.parseInt(consola.nextLine());
				Cliente cliente = clienteService.buscarClienteporId(codigo);
				//guardar si no es null
				if (cliente != null) {
					logger.info("Ingrese el nombre del cliente: ");
					var nombre = consola.nextLine();
					logger.info("Ingrese el apellido del cliente: ");
					var apellido = consola.nextLine();
					logger.info("Ingrese el telefono del cliente: ");
					var telefono = consola.nextLine();
					logger.info("Ingrese el correo del cliente: ");
					var correo = consola.nextLine();
					logger.info("Ingrese el genero del cliente: ");
					var genero = consola.nextLine();
					logger.info("Ingrese la edad del cliente: ");
					var edad = Integer.parseInt(consola.nextLine());
					cliente.setNombre(nombre);
					cliente.setApellido(apellido);
					cliente.setTelefono(telefono);
					cliente.setCorreo(correo);
					cliente.setGenero(genero);
					cliente.setEdad(edad);
					clienteService.guardarCliente(cliente);
					logger.info("Cliente modificado: " + cliente+salto);
				}else {
					logger.info("Cliente NO encontrado: " + cliente+salto);
				}
			}
			case 5 -> {
				logger.info("***==Eliminar Cliente==***" +salto);
				logger.info("Ingrese el codigo del cliente a eliminar: ");
				var codigo = Integer.parseInt(consola.nextLine());
				var cliente = clienteService.buscarClienteporId(codigo);
				if (cliente != null) {
					clienteService.eliminarCliente(cliente);
					logger.info("Cliente eliminado correctamente: " + cliente+salto);
				}else {
					logger.info("Cliente NO encontrado: " + cliente+salto);
				}
			}
			case 6 -> {
				logger.info("Hasta la proxima papapapapapapapapapa" +salto+salto);
				salir = true;
			}
			default -> logger.info("Opcion invalida, vuelva a intentar");
		}
		return false;
	}
}
