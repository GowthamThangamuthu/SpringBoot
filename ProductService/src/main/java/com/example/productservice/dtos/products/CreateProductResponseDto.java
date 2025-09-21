package com.example.productservice.dtos.products;


import com.example.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductResponseDto {
    private Long Id;
    private String Title;
    private String Description;
    private Double Price;
    private String ImageUrl;

    public static CreateProductResponseDto fromProduct(Product product) {
        CreateProductResponseDto ResponseDto = new CreateProductResponseDto();
        ResponseDto.setId(product.getId());
        ResponseDto.setTitle(product.getTitle());
        ResponseDto.setDescription(product.getDescription());
        ResponseDto.setPrice(product.getPrice());
        ResponseDto.setImageUrl(product.getImageUrl());
        return ResponseDto;
    }
}
