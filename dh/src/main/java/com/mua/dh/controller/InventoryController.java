package com.mua.dh.controller;

import com.mua.dh.dto.ProductInventoryDto;
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

    @PostMapping("/checkout")
    private List<ProductInventoryDto> checkout(@RequestBody List<ProductInventoryDto> productList){
        return service.checkout(productList);
    }

}
