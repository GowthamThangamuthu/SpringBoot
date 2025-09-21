package com.example.productservice.dtos.products;

import com.example.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetProductDto {
    private Long Id;
    private String Title;
    private String Description;
    private Double Price;
    private String ImageUrl;

    public static GetProductDto from(Product product) {
        GetProductDto getProductDto = new GetProductDto();
        getProductDto.Id = product.getId();
        getProductDto.Title = product.getTitle();
        getProductDto.Description = product.getDescription();
        getProductDto.Price = product.getPrice();
        getProductDto.ImageUrl = product.getImageUrl();

        return getProductDto;
    }
}
