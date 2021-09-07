package com.mua.dh;

import com.mua.dh.model.LoginCredential;
import com.mua.dh.model.User;
import com.mua.dh.repo.LoginCredentialRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DhApplication implements CommandLineRunner {


	@Autowired
	LoginCredentialRepo loginCredentialRepo;

	public static void main(String[] args) {
		SpringApplication.run(DhApplication.class, args);
	}

	@Override
	public void run(String... args) {
		if(loginCredentialRepo.findAll().size()==0){
			createSuperUser();
		}
	}

	void createSuperUser(){
		String username="admin";
		String password="admin";
		LoginCredential loginCredential = new LoginCredential();
		User user = new User();
		loginCredential.setUser(user);
		user.setLoginCredential(loginCredential);
		loginCredential.setUsername(username);
		loginCredential.setPassword(password);
		user.setUsername(username);
		loginCredential.setRole("ROLE_ADMIN");

		loginCredentialRepo.save(loginCredential);
	}

}
