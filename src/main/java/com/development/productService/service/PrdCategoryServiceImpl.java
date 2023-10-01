package com.development.productService.service;

import com.development.productService.model.Category;
import com.development.productService.model.Price;
import com.development.productService.model.Product;
import com.development.productService.productRequest.CategoryRequest;
import com.development.productService.productRequest.ProductRequest;
import com.development.productService.productResponse.CategoryResponse;
import com.development.productService.productResponse.ProductResponse;
import com.development.productService.repository.CategoryRepository;
import com.development.productService.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("PrdCategoryServiceImpl")
public class PrdCategoryServiceImpl implements PrdCategoryService {

    private  final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    @Autowired
    public PrdCategoryServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<ProductRequest> getAllProducts() {
        return null;
    }

    @Override
    public ProductResponse addProduct(ProductRequest productReqst) {
        Product product = mapToProduct(productReqst);
        product=productRepository.save(product);
        return mapToProductResponse(product);
    }

    private ProductResponse mapToProductResponse(Product product) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setTitle(product.getTitle());
        productResponse.setDescription(product.getDescription());
        Price price = product.getPrice();
        productResponse.setPrice(price.getPrice());
        productResponse.setCurrency(price.getCurrency());
        productResponse.setImage(product.getImage());
        Category category = product.getCategory();
        List<Product> productList  = category.getProducts();
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setId(category.getId());
        categoryResponse.setName(category.getName());
        productResponse.setCategoryResponse(categoryResponse);
        return productResponse;
    }

    private Product mapToProduct(ProductRequest productReq) {
        Product product = new Product();
        product.setTitle(productReq.getTitle());
        product.setDescription(productReq.getDescription());
        Price price = new Price();
        price.setPrice(productReq.getPrice());
        price.setCurrency(productReq.getCurrency());
        product.setPrice(price);
        product.setImage(productReq.getImage());
      CategoryRequest categoryRequest= productReq.getCategoryRequest();

        Category category = null;
        if (categoryRequest != null && categoryRequest.getName() != null) {

           category = categoryRepository.findByName(categoryRequest.getName());
        }
        if (category == null) {
            category = new Category();
            assert categoryRequest != null;
            category.setName(categoryRequest.getName());
        }

        category.getProducts().add(product);
        product.setCategory(category);

        //product.setCategory(category);
        return product;

    }
}
