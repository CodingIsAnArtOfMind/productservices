package com.development.productService.productRequest;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Setter
@Getter
public class CategoryRequest {
    private UUID id;
    private String name;

}
