package com.example.productservice.services;

import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import com.example.productservice.repositories.CategoryRepository;
import com.example.productservice.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("DBProductService")
public class ProductServiceDBImpl implements ProductService {

    private ProductRepository  productRepository;
    private CategoryRepository categoryRepository;

    public ProductServiceDBImpl(ProductRepository productRepository,  CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product createProduct(Product product) {
        Category toBePutInProduct = getCategoryOrCreate(product);

        product.setCategory(toBePutInProduct);

        Product savedProduct =  productRepository.save(product);
        return savedProduct;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product partialUpdateProduct(Long ProductId, Product product) {

        Optional<Product> productToUpdate = productRepository.findById(ProductId);

        if(productToUpdate.isEmpty()){
            throw new ProductNotFoundException("Product not found with id: " + ProductId);
        }

        if(product.getTitle() != null) {
            productToUpdate.get().setTitle(product.getTitle());
        }

        if(product.getDescription() != null) {
            productToUpdate.get().setDescription(product.getDescription());
        }

        if(product.getPrice() != null){
            productToUpdate.get().setPrice(product.getPrice());
        }

        if(product.getCategory() != null) {
            Category toBePutInProduct = getCategoryOrCreate(product);

            productToUpdate.get().setCategory(toBePutInProduct);
        }


        return productRepository.save(productToUpdate.get());
    }

    private Category getCategoryOrCreate(Product product) {
        String categoryName = product.getCategory().getName();

        Optional<Category> category = categoryRepository.findByName(categoryName);
        Category toBePutInProduct = null;

        if(category.isEmpty()){
            Category toSaveCategory = new Category();
            toSaveCategory.setName(categoryName);

            toBePutInProduct = categoryRepository.save(toSaveCategory);
        } else {
            toBePutInProduct = category.get();
        }
        return toBePutInProduct;
    }

}
