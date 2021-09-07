package com.mua.dh.service;

import com.mua.dh.dto.ProductInventoryDto;
import com.mua.dh.model.Product;
import com.mua.dh.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {

    @Autowired
    private ProductRepo productRepo;

    public List<ProductInventoryDto> checkout(@RequestBody List<ProductInventoryDto> productList) {
        List<ProductInventoryDto> result = new ArrayList<>();
        for (ProductInventoryDto productInventoryDto :
                productList) {
            if (productInventoryDto.getQuantity() >= 0) {
                Optional<Product> productOptional = productRepo.findById(productInventoryDto.getProductId());
                if (productOptional.isPresent()) {
                    Product product = productOptional.get();
                    if (product.getCountAvailability() >= productInventoryDto.getQuantity()) {
                        product.setCountAvailability(product.getCountAvailability() - productInventoryDto.getQuantity());
                        productRepo.save(product);
                        result.add(productInventoryDto);
                    }
                }
            }
        }
        return result;
    }

    public Product restock(ProductInventoryDto productInventoryDto) {
        Optional<Product> productOptional = productRepo.findById(productInventoryDto.getProductId());
        if(productOptional.isPresent()){
            Product product = productOptional.get();
            if(productInventoryDto.getNewSellingPrice()!=null && productInventoryDto.getNewSellingPrice()>0){
                product.setSellingPrice(productInventoryDto.getNewSellingPrice());
            }
            if(productInventoryDto.getQuantity()>0){
                product.setCountAvailability(productInventoryDto.getQuantity());
            }
            productRepo.save(product);
        }
        return new Product();
    }
    public Product soldOut(ProductInventoryDto productInventoryDto) {
        Optional<Product> productOptional = productRepo.findById(productInventoryDto.getProductId());
        if(productOptional.isPresent()) {
            Product product = productOptional.get();
            product.setCountAvailability(0L);
            return productRepo.save(product);
        }
        return new Product();
    }

}