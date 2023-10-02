package com.development.productService.service;

import com.development.productService.productRequest.ProductRequest;
import com.development.productService.productResponse.ProductResponse;

import java.util.List;

public interface PrdCategoryService {
    List<ProductResponse> getAllProducts();

    ProductResponse addProduct(ProductRequest product);

    List<ProductResponse> getProductsByCategory(String category);

    List<String> getAllCategories();

    ProductResponse getProductById(String id);

    ProductResponse updateProduct(ProductRequest product, String id);

    ProductResponse deleteProduct(String id);
}
