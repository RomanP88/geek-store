package com.geekbrains.geekstore.dto;


import com.geekbrains.geekstore.model.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDto extends Product {

    private Long id;
    private String title;
    private int cost;
    private String categoryTitle;


    public ProductDto(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.cost = product.getCost();
        this.categoryTitle = product.getCategory().getTitle();
    }
}
