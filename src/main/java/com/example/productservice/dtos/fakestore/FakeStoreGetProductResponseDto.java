package com.example.productservice.dtos.fakestore;

import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreGetProductResponseDto {

    private Long id;
    private String title;
    private String description;
    private String category;
    private double price;
    private String image;

    public Product toProduct() {

        Product product1 = new Product();
        product1.setId(this.getId());
        product1.setTitle(this.getTitle());
        product1.setDescription(this.getDescription());
        product1.setPrice(this.getPrice());
        Category category = new Category();
        category.setName(this.getCategory());
        product1.setCategory(category);

        return product1;
    }
}
