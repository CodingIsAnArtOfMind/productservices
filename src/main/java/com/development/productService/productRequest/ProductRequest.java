package com.development.productService.productRequest;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class ProductRequest {
    private UUID id;
    private String title;
    private String description;
    private String image;
    private CategoryRequest categoryRequest;
    private String currency;
    private double price;
}
