package com.alexandrianuevo.BackendAlexandriaNuevo;

import com.alexandrianuevo.BackendAlexandriaNuevo.model.Libro;
import com.alexandrianuevo.BackendAlexandriaNuevo.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendAlexandriaNuevoApplication implements CommandLineRunner {

	@Autowired
	private LibroRepository libroRepository;

	public static void main(String[] args) {
		SpringApplication.run(BackendAlexandriaNuevoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("SERVIDOR LISTO");
	}
}

