package com.development.productService.productResponse;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class CategoryResponse {
    private UUID id;
    private String name;
}
