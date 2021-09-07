package com.mua.dh.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long warehouseId;
    private String name;
    private String address;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Area area;
    private String contactInfo;
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Product> listOfAvailableProducts;
    //A sourcing price specific to this warehouse for each of the available products
    //handled in Product
}
