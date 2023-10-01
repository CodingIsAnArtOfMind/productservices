package com.development.productService.controller;

import com.development.productService.productRequest.ProductRequest;
import com.development.productService.service.PrdCategoryService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("product/category")
public class PrdCategoryController {
    private PrdCategoryService prdCategoryService;

    public PrdCategoryController(@Qualifier("PrdCategoryServiceImpl") PrdCategoryService prdCategoryService) {
        this.prdCategoryService = prdCategoryService;
    }

    @GetMapping("getAllProduct")
    public ResponseEntity getAllProducts() {
        return new ResponseEntity<>(prdCategoryService.getAllProducts(), HttpStatus.OK);
    }

    @PostMapping("/createProduct")
    public ResponseEntity createProduct(@RequestBody ProductRequest product) {
        return new ResponseEntity<>(prdCategoryService.addProduct(product), HttpStatus.CREATED);
    }
}
