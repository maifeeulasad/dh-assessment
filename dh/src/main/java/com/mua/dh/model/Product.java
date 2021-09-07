package com.mua.dh.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mua.dh.dto.NewProductInventory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
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

    public Product(NewProductInventory newProductInventory){
        setName(newProductInventory.getName());
        setDescription(newProductInventory.getDescription());
        setSellingPrice(newProductInventory.getSellingPrice());
        setSourcingPrice(newProductInventory.getSourcingPrice());
        setCountAvailability(newProductInventory.getCountAvailability());
        setInventory(newProductInventory.getInventory());
        setWarehouses(newProductInventory.getWarehouses());
    }
}
