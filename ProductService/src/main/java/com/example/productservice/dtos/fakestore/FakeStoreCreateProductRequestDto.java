package com.example.productservice.dtos.fakestore;

import com.example.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreCreateProductRequestDto {
    private String title;
    private String description;
    private String category;
    private double price;
    private String image;

    public static FakeStoreCreateProductRequestDto fromProduct(Product product) {

        FakeStoreCreateProductRequestDto fakeStoreCreateProductRequestDto = new FakeStoreCreateProductRequestDto();

        fakeStoreCreateProductRequestDto.title = product.getTitle();
        fakeStoreCreateProductRequestDto.price = product.getPrice();
        fakeStoreCreateProductRequestDto.description = product.getDescription();
        fakeStoreCreateProductRequestDto.category = product.getCategory().getName();
        fakeStoreCreateProductRequestDto.image = product.getImageUrl();

        return fakeStoreCreateProductRequestDto;
    }

}
