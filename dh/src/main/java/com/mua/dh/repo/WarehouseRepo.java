package com.mua.dh.repo;

import com.mua.dh.model.Product;
import com.mua.dh.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WarehouseRepo extends JpaRepository<Warehouse,Long> {
    @Query("select warehouse.listOfAvailableProducts from Warehouse warehouse where warehouse.warehouseId=?1")
    List<Product> findAllProductsByWarehouseId(Long warehouseId);
    @Query("select warehouse.listOfAvailableProducts from Warehouse warehouse where warehouse.area.areaId=?1")
    List<Product> findAllProductsByAreaId(Long areaId);
    @Query("select warehouse.listOfAvailableProducts from Warehouse warehouse where warehouse.warehouseId=?1 and warehouse.area.areaId=?2")
    List<Product> findAllProductsByWarehouseIdAndAreaId(Long warehouseId,Long areaId);
}
