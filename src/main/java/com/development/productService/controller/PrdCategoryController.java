package com.development.productService.controller;

import com.development.productService.exception.InValidPatternException;
import com.development.productService.exception.NotFoundException;
import com.development.productService.productRequest.ProductRequest;
import com.development.productService.productResponse.ProductResponse;
import com.development.productService.service.PrdCategoryService;
import com.development.productService.utility.ValidateUuid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("product/category")
public class PrdCategoryController {
    private PrdCategoryService prdCategoryService;

    public PrdCategoryController(@Qualifier("PrdCategoryServiceImpl") PrdCategoryService prdCategoryService) {
        this.prdCategoryService = prdCategoryService;
    }

    @GetMapping("getAllProduct")
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return new ResponseEntity<>(prdCategoryService.getAllProducts(), HttpStatus.OK);
    }

    @PostMapping("/createProduct")
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest product) {
        return new ResponseEntity<>(prdCategoryService.addProduct(product), HttpStatus.CREATED);
    }

    @GetMapping("productByCategory/{categoryId}")
    public ResponseEntity<List<ProductResponse>> getProductsByCategory(@PathVariable("categoryId") String categoryId){
        return new ResponseEntity<>(prdCategoryService.getProductsByCategory(categoryId), HttpStatus.OK);
    }

    @GetMapping("/allCategories")
    public ResponseEntity<List<String>> getAllCategories() {
        return new ResponseEntity<>(prdCategoryService.getAllCategories(), HttpStatus.OK);
    }

    @GetMapping("/getProductById/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable("id") String id) throws InValidPatternException {
        ValidateUuid.isValidUUID(id);
        return new ResponseEntity<>(prdCategoryService.getProductById(id), HttpStatus.OK);
    }

    @PutMapping("updateProductById/{id}")
    public ResponseEntity<ProductResponse> updateProductById(@PathVariable("id") String id,@RequestBody ProductRequest product)throws InValidPatternException {
        ValidateUuid.isValidUUID(id);
        return new ResponseEntity<>(prdCategoryService.updateProduct(product,id), HttpStatus.OK);
    }
    @DeleteMapping("deleteProductById/{id}")
    public ResponseEntity<ProductResponse> deleteProductById(@PathVariable("id") String id) throws NotFoundException,InValidPatternException {
        ValidateUuid.isValidUUID(id);
        return new ResponseEntity<>(prdCategoryService.deleteProduct(id), HttpStatus.OK);
    }
}
