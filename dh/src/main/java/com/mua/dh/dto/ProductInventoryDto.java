package com.mua.dh.dto;

import com.mua.dh.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductInventoryDto {
    private Long productId;
    private Long quantity;
}
