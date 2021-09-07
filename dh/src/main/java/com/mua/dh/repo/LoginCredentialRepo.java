package com.mua.dh.repo;

import com.mua.dh.model.LoginCredential;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoginCredentialRepo extends JpaRepository<LoginCredential,Long> {
    List<LoginCredential>findByUsername(String username);
    List<LoginCredential>findByUsernameAndPassword(String username,String password);
}
