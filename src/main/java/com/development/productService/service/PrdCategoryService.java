package com.development.productService.service;

import com.development.productService.productRequest.ProductRequest;
import com.development.productService.productResponse.ProductResponse;

import java.util.List;

public interface PrdCategoryService {
    List<ProductRequest> getAllProducts();

    ProductResponse addProduct(ProductRequest product);
}
