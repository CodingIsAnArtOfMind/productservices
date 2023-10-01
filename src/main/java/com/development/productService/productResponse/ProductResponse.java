package com.development.productService.productResponse;

import com.development.productService.productRequest.CategoryRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Setter
@Getter
public class ProductResponse {
    private UUID id;
    private String title;
    private String description;
    private String image;
    private CategoryResponse categoryResponse;
    private String currency;
    private double price;


}
