package com.mua.dh.repo;

import com.mua.dh.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product,Long> {
    @Query("select product from Product product where product.name like ?1 or product.description like ?2")
    List<Product>findAllByNameAndDescription(String name, String description);
}
