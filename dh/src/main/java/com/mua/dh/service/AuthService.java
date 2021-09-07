package com.mua.dh.service;

import com.mua.dh.model.LoginCredential;
import com.mua.dh.model.User;
import com.mua.dh.repo.LoginCredentialRepo;
import com.mua.dh.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {

    @Autowired
    private LoginCredentialRepo loginCredentialRepo;
    @Autowired
    private JwtTokenProvider tokenProvider;

    public Boolean join(LoginCredential loginCredential){
        List<LoginCredential> loginCredentials = loginCredentialRepo.findByUsername(loginCredential.getUsername());
        if(loginCredentials!=null && loginCredentials.size()!=0){
            return false;
        }
        loginCredential.setLoginCredentialId(null);
        loginCredential.setUser(new User());
        try{
            loginCredentialRepo.save(loginCredential);
            return true;
        }catch (Exception ignored){
            return false;
        }
    }

    public String login(LoginCredential loginCredential){
        List<LoginCredential> loginCredentials = loginCredentialRepo.findByUsernameAndPassword(loginCredential.getUsername(), loginCredential.getPassword());
        if(loginCredentials!=null && loginCredentials.size()!=0){
            return tokenProvider.generateToken(loginCredential.getUsername());
        }
        return "";
    }

}
