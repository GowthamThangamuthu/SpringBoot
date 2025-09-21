package com.example.productservice.dtos.products;

import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductRequestDto {
    private String Title;
    private String Description;
    private String CategoryName;
    private Double Price;
    private String ImageUrl;


    public Product toProduct(){
        Product product = new Product();
        product.setTitle(this.Title);
        product.setDescription(this.Description);
        product.setPrice(this.Price);
        product.setImageUrl(this.ImageUrl);
        Category category = new Category();
        category.setName(CategoryName);
        product.setCategory(category);


        return product;
    }


}
