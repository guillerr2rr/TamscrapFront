package com.Tamscrap.Tamscrap;

import com.Tamscrap.Tamscrap.Model.Cliente;
import com.Tamscrap.Tamscrap.Model.Producto;
import com.Tamscrap.Tamscrap.Model.UserAuthority;
import com.Tamscrap.Tamscrap.repository.ClienteRepo;
import com.Tamscrap.Tamscrap.repository.ProductoRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class TamscrapApplication {

	public static void main(String[] args) {
		SpringApplication.run(TamscrapApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(
			ClienteRepo userRepo,
			ProductoRepo productRepo,
			PasswordEncoder encoder
	) {
		return args -> {
			Cliente user1 = new Cliente(
					"user1", encoder.encode("1234"), "user1@localhost", new ArrayList<>(List.of(UserAuthority.ADMIN, UserAuthority.USER))
			);
			Cliente user2 = new Cliente(
					"user2", encoder.encode("1234"), "user2@localhost", new ArrayList<>(List.of(UserAuthority.USER))
			);
			userRepo.save(user1);
			userRepo.save(user2);

			List<Producto> productos = List.of(
					new Producto("Prod1", 3.99, "https://dummyimage.com/200x200/fff/aaa"),
					new Producto("Prod2", 4.99, "https://dummyimage.com/200x200/fff/aaa"),
					new Producto("Prod3", 7.99, "https://dummyimage.com/200x200/fff/aaa"),
					new Producto("Prod4", 8.99, "https://dummyimage.com/200x200/fff/aaa"),
					new Producto("Prod5", 9.99, "https://dummyimage.com/200x200/fff/aaa"),
					new Producto("Prod6", 1.99, "https://dummyimage.com/200x200/fff/aaa")
			);

			productRepo.saveAll(productos);

		};


	}
}