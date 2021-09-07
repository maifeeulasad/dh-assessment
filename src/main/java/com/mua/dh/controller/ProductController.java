package com.mua.dh.controller;

import com.mua.dh.model.Product;
import com.mua.dh.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/all")
    private List<Product> getAll(@RequestParam(required = false) Long warehouseId,@RequestParam(required = false) Long areaId){
        return service.getProducts(warehouseId, areaId);
    }

    @GetMapping("/search")
    private List<Product> search(@RequestParam(required = false) String name,@RequestParam(required = false) String description){
        return service.searchProducts(name, description);
    }

}
