package com.mua.dh.controller;

import com.mua.dh.dto.NewProductInventory;
import com.mua.dh.dto.ProductInventoryDto;
import com.mua.dh.model.Product;
import com.mua.dh.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService service;

    @PostMapping("/add")
    private Product add(@RequestBody NewProductInventory newProductInventory){
        return service.add(newProductInventory);
    }

    @PostMapping("/checkout")
    private List<ProductInventoryDto> checkout(@RequestBody List<ProductInventoryDto> productList){
        return service.checkout(productList);
    }

    @PostMapping("/restock")
    private Product restock(@RequestBody ProductInventoryDto productInventoryDto){
        return service.restock(productInventoryDto);
    }

    @PostMapping("/sold")
    private Product soldOut(@RequestBody ProductInventoryDto productInventoryDto){
        return service.soldOut(productInventoryDto);
    }
}
