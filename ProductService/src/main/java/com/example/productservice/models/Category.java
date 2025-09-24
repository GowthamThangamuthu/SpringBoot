package com.example.productservice.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel{
    private String name;
    private String description;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Product> featuredProducts;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST,mappedBy = "category")
    @Fetch(FetchMode.SELECT)
    private List<Product> allproducts;

    @OneToOne(cascade = {})
    private Subcategory subcategories;

}
