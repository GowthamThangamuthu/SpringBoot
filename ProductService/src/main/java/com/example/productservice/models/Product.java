package com.example.productservice.models;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Where;

@Getter
@Setter
@Entity
@Where(clause = "is_deleted = false")
public class Product extends BaseModel {
    private String title;
    private String description;
    private Double price;
    private String imageUrl;

    @ManyToOne
    @Cascade({CascadeType.PERSIST})
    private Category category;
}
