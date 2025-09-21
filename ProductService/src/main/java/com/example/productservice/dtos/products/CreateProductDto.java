package com.example.productservice.dtos.products;

import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductDto {

    private Long Id;
    private String Title;
    private String Description;
    private Double Price;
    private String ImageUrl;
    private String CategoryName;

    public static CreateProductDto fromProduct(Product product) {
        CreateProductDto responseDto = new CreateProductDto();
        responseDto.setId(product.getId());
        responseDto.setTitle(product.getTitle());
        responseDto.setDescription(product.getDescription());
        responseDto.setPrice(product.getPrice());
        responseDto.setImageUrl(product.getImageUrl());
        responseDto.setCategoryName(product.getCategory().getName());
        return responseDto;
    }

    public Product toProduct() {
        Product product = new Product();
        product.setTitle(this.Title);
        product.setDescription(this.Description);
        product.setPrice(this.Price);
        product.setImageUrl(this.ImageUrl);
        Category  category = new Category();
        category.setName(this.CategoryName);
        product.setCategory(category);

        return product;
    }
}
