package com.example.productservice.controllers;


import com.example.productservice.dtos.products.*;
import com.example.productservice.models.Product;
import com.example.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    public ProductController(@Qualifier("DBProductService") ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/")
    public CreateProductResponseDto CreateProduct(@RequestBody CreateProductRequestDto createProductRequestDto) {
       Product product = productService.createProduct(createProductRequestDto.toProduct());
       return CreateProductResponseDto.fromProduct(product);

    }

    @GetMapping("/")
    public GetAllProductsResponseDto GetAllProducts() {

        List<Product>  products = productService.getAllProducts();
        GetAllProductsResponseDto response = new GetAllProductsResponseDto();

        List<GetProductDto> getProductResponseDtos = new ArrayList<>();

        for(Product product : products){
            getProductResponseDtos.add(GetProductDto.from(product));
        }

        response.setProducts(getProductResponseDtos);

        return response;
    }

    @PatchMapping("/{id}")
    public PatchProductResponseDto UpdateProduct(
            @PathVariable("id") Long productId,
            @RequestBody CreateProductDto productDto
    ) {

        Product product = productService.partialUpdateProduct(
                productId,
                productDto.toProduct());

        PatchProductResponseDto response = new PatchProductResponseDto();
        response.setProduct(GetProductDto.from(product));

        return response;
    }


    @DeleteMapping("/{id}")
    public String SoftDeleteProduct(@PathVariable("id") Long ProductId){
        productService.deleteProduct(ProductId);
        return "ProductID : " + ProductId + " got deleted successfully";
    }


//    @RequestMapping(name = "NAMAN", value = "/naman")
//    public String naman(){
//        return "Customized Request";
//    }


}
