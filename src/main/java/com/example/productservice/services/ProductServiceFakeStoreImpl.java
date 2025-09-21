package com.example.productservice.services;

import com.example.productservice.dtos.fakestore.FakeStoreCreateProductRequestDto;
import com.example.productservice.dtos.fakestore.FakeStoreGetProductResponseDto;
import com.example.productservice.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service("FakeStoreProductService")
public class ProductServiceFakeStoreImpl implements ProductService {

    private RestTemplate restTemplate;

    public ProductServiceFakeStoreImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Override
    public Product createProduct(Product product) {

        FakeStoreCreateProductRequestDto request  = new FakeStoreCreateProductRequestDto();
        request.setTitle(product.getTitle());
        request.setPrice(product.getPrice());
        request.setImage(product.getImageUrl());
        request.setCategory(product.getCategory().getName());
        request.setDescription(product.getDescription());


        FakeStoreGetProductResponseDto response = restTemplate.postForObject(
                "https://fakestoreapi.com/products",
                request,
                FakeStoreGetProductResponseDto.class
        );

        Product product1 = new Product();

        return response.toProduct();
    }

    @Override
    public List<Product> getAllProducts() {

        FakeStoreGetProductResponseDto[] response = restTemplate.getForObject(
                "https://fakestoreapi.com/products",
                FakeStoreGetProductResponseDto[].class
        );

        List<FakeStoreGetProductResponseDto> responseDtoList = Stream.of(response).toList();

        List<Product> products = new ArrayList<>();

        for (FakeStoreGetProductResponseDto fakeStoreGetProductResponseDto : responseDtoList){
            products.add(fakeStoreGetProductResponseDto.toProduct());
        }

        return products;
    }


    @Override
    public Product partialUpdateProduct(Long productId, Product product) {
        FakeStoreGetProductResponseDto productResponseDto = restTemplate.patchForObject(
                "https://fakestoreapi.com/products/" + productId,
                FakeStoreCreateProductRequestDto.fromProduct(product),
                FakeStoreGetProductResponseDto.class
        );

        return productResponseDto.toProduct();

    }

}
