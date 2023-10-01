package com.development.productService.productRequest;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GenericProductRequest {
    private Long id;
    private String title;
    private String description;
    private String image;
    private String category;
    private double price;
}
