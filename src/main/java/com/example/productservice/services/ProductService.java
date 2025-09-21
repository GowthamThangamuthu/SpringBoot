package com.example.productservice.services;

import com.example.productservice.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProductService {
    Product createProduct(Product product);

    List<Product> getAllProducts();

    Product partialUpdateProduct(Long ProductId, Product product);
}
