package com.mua.dh.service;

import com.mua.dh.model.Product;
import com.mua.dh.repo.ProductRepo;
import com.mua.dh.repo.WarehouseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private WarehouseRepo warehouseRepo;

    public List<Product>getProducts(Long warehouseId, Long areaId){
        if(warehouseId==null && areaId==null){
            return getAll();
        }else if(warehouseId!=null && areaId!=null){
            return getAllProductsByWarehouseIdAndAreaId(warehouseId,areaId);
        }else if(warehouseId!=null){
            return getAllByWarehouseId(warehouseId);
        }else {
            return getAllByAreaId(areaId);
        }
    }

    public List<Product>searchProducts(String name,String description){
        if(name==null){
            name ="";
        }
        if(description==null){
            description="";
        }
        return productRepo.findAllByNameAndDescription(name,description);
    }

    private List<Product> getAll(){
        return productRepo.findAll();
    }

    private List<Product> getAllByWarehouseId(Long warehouseId){
        return warehouseRepo.findAllProductsByWarehouseId(warehouseId);
    }

    private List<Product> getAllByAreaId(Long areaId){
        return warehouseRepo.findAllProductsByAreaId(areaId);
    }

    private List<Product> getAllProductsByWarehouseIdAndAreaId(Long warehouseId,Long areaId){
        return warehouseRepo.findAllProductsByWarehouseIdAndAreaId(warehouseId, areaId);
    }

}
