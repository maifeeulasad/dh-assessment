package com.mua.dh.service;

import com.mua.dh.dto.NewProductInventory;
import com.mua.dh.dto.ProductInventoryDto;
import com.mua.dh.model.Product;
import com.mua.dh.model.User;
import com.mua.dh.model.UserPrincipal;
import com.mua.dh.model.UserType;
import com.mua.dh.repo.ProductRepo;
import com.mua.dh.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {

    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private UserRepo userRepo;

    public Product add(NewProductInventory newProductInventory){
        Product product = new Product(newProductInventory);
        if(isAdmin()){
            return productRepo.save(product);
        }
        return new Product();
    }

    public List<ProductInventoryDto> checkout(@RequestBody List<ProductInventoryDto> productList) {
        final List<ProductInventoryDto> result = new ArrayList<>();

        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.isAuthenticated()) {
            UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
            User user = userRepo.findByUsername(principal.getUsername());
            List<Product> productsAdded = new ArrayList<>();
            if (user!=null) {
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
                                productsAdded.add(product);
                            }
                        }
                    }
                }
                // todo: this will throw error when same product is added on multiple checkout,
                // need to handle with a Checkout/Purchase entity
                List<Product> products = user.getProductsBought();
                products.addAll(productsAdded);
                user.setProductsBought(products);
            }
        }
        return result;
    }

    public Product restock(ProductInventoryDto productInventoryDto) {
        if(isAdmin()){
            Optional<Product> productOptional = productRepo.findById(productInventoryDto.getProductId());
            if(productOptional.isPresent()){
                Product product = productOptional.get();
                if(productInventoryDto.getNewSellingPrice()!=null && productInventoryDto.getNewSellingPrice()>0){
                    product.setSellingPrice(productInventoryDto.getNewSellingPrice());
                }
                if(productInventoryDto.getQuantity()>0){
                    product.setCountAvailability(productInventoryDto.getQuantity());
                }
                return productRepo.save(product);
            }
        }
        return new Product();
    }
    public Product soldOut(ProductInventoryDto productInventoryDto) {
        if(isAdmin()){
            Optional<Product> productOptional = productRepo.findById(productInventoryDto.getProductId());
            if(productOptional.isPresent()) {
                Product product = productOptional.get();
                product.setCountAvailability(0L);
                return productRepo.save(product);
            }
        }
        return new Product();
    }

    private Boolean isAdmin(){
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.isAuthenticated()) {
            UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
            User user = userRepo.findByUsername(principal.getUsername());
            return user.getLoginCredential().getRole()== UserType.ADMIN;
        }
        return false;
    }

}