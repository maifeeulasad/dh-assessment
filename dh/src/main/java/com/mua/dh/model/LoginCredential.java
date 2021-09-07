package com.mua.dh.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class LoginCredential {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long loginCredentialId;

    @Column(nullable = false)
    private String username;
    // todo: encrypt this field
    @Column(nullable = false)
    private String password;

    public String role="ROLE_USER";

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private User user;

    public LoginCredential(String username,String password){
        this.username=username;
        this.password=password;
    }
}