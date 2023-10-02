package com.development.productService.service;

import com.development.productService.exception.NotFoundException;
import com.development.productService.model.Category;
import com.development.productService.model.Price;
import com.development.productService.model.Product;
import com.development.productService.productRequest.CategoryRequest;
import com.development.productService.productRequest.ProductRequest;
import com.development.productService.productResponse.CategoryResponse;
import com.development.productService.productResponse.ProductResponse;
import com.development.productService.repository.CategoryRepository;
import com.development.productService.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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
    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAllProducts();
        return products.stream().map(this::mapToProductResponse).collect(Collectors.toList());
    }

    @Override
    public ProductResponse addProduct(ProductRequest productReqst) {
        Product product = mapToProduct(productReqst);
        product=productRepository.save(product);
        return mapToProductResponse(product);
    }
    @Transactional
    @Override
    public List<ProductResponse> getProductsByCategory(String categoryName)  {
        List<Product> productList = productRepository.getAllProductByCategory(categoryName);
        if(productList==null || productList.isEmpty()){
            throw new NotFoundException("category Name:- "+categoryName + "Not Found inDB");
        }
        return productList.stream().map(this::mapToProductResponse).collect(Collectors.toList());
    }

    @Override
    public List<String> getAllCategories() {
        return productRepository.getAllProductCategory();
    }

    @Override
    public ProductResponse getProductById(String id) {
        Product product = productRepository.findById(UUID.fromString(id)).orElse(null);
        if(product != null){
            return mapToProductResponse(product);
        }
        else
            throw new NotFoundException("Product with  id "+id+" not found");
    }

    @Override
    public ProductResponse updateProduct(ProductRequest productRequest, String id) {
        Optional<Product> optionalPrd = productRepository.findById(UUID.fromString(id));

        if (optionalPrd.isPresent()) {
            Product existingProduct = optionalPrd.get();
            existingProduct.setDescription(productRequest.getDescription());
            existingProduct.setTitle(productRequest.getTitle());
            Price price = existingProduct.getPrice();
            if (price == null) {
                price = new Price();
            }
            price.setPrice(productRequest.getPrice());
            price.setCurrency(productRequest.getCurrency());
            existingProduct.setPrice(price);
            existingProduct.setImage(productRequest.getImage());

            CategoryRequest categoryRequest = productRequest.getCategoryRequest();
            Category category = null;

            if (categoryRequest != null && categoryRequest.getName() != null) {
                category = categoryRepository.findByName(categoryRequest.getName());
            }
            if (category == null) {
                category = new Category();
                assert categoryRequest != null;
                category.setName(categoryRequest.getName());
            }

            //Adding the product inside the category model class
            category.getProducts().add(existingProduct);
            existingProduct.setCategory(category);

            existingProduct = productRepository.save(existingProduct);

            return mapToProductResponse(existingProduct);
        }
        else
            throw new NotFoundException("Product with  id "+id+" doesn't exist in db");
    }

    @Override
    public ProductResponse deleteProduct(String id) {
        Product product = productRepository.findById(UUID.fromString(id)).orElse(null);
        if(product != null){
            productRepository.deleteById(UUID.fromString(id));
            return mapToProductResponse(product);
        }
        else throw new NotFoundException("Product with  id "+id+" doesn't exist in db");
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
       // List<Product> productList  = category.getProducts();
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
