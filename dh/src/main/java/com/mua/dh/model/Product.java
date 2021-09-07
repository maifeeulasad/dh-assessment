package com.mua.dh.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;
    private String name;
    private Double sellingPrice;
    private Long countAvailability;
    @JsonIgnore
    private Double sourcingPrice;
    @Column(length = 4096)
    private String description;
    // entity wasn't described
    private String inventory;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Warehouse> warehouses;
}
