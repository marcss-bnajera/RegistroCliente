package org.kinscript.registro_Cliente;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;


@SpringBootApplication
public class RegistroClienteApplication implements CommandLineRunner {
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
		return 0;
	}

	private boolean ejecutarOpciones(Scanner consola, int opcion) {
		return false;
	}
}
