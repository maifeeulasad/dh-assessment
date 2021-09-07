package com.mua.dh.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mua.dh.model.Warehouse;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
public class NewProductInventory {
    private String name;
    private Double sellingPrice;
    private Double sourcingPrice;
    private Long countAvailability;
    private String description;
    // entity wasn't described
    private String inventory;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Warehouse> warehouses;
}
