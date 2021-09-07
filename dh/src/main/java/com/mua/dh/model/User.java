package com.mua.dh.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @Column(unique = true,nullable = false)
    private String username;
    @Column(updatable = false)
    @CreationTimestamp
    private Date joiningDate;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private LoginCredential loginCredential;
}