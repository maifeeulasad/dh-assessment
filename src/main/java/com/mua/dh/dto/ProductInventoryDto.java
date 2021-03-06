package com.mua.dh.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductInventoryDto {
    private Long productId;
    private Long quantity;
    private Double newSellingPrice;
}
