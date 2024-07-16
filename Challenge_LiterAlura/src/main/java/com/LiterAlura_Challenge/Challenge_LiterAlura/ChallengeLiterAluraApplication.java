package com.LiterAlura_Challenge.Challenge_LiterAlura;

import com.LiterAlura_Challenge.Challenge_LiterAlura.principal.Principal;
import com.LiterAlura_Challenge.Challenge_LiterAlura.repositorio.AutorRepository;
import com.LiterAlura_Challenge.Challenge_LiterAlura.repositorio.LibrosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Set;

@SpringBootApplication
public class ChallengeLiterAluraApplication implements CommandLineRunner {

	@Autowired
	private LibrosRepository repository;
	@Autowired
	private AutorRepository repositoryAutor;

	public static void main(String[] args) {
		SpringApplication.run(ChallengeLiterAluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repository,repositoryAutor);
		principal.opcionesMenu();


	}
}

