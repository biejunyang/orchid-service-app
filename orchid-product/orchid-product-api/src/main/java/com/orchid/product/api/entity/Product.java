package com.orchid.product.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private Long id;

    private String name;

    private String code;

    private String title;

    private Double price;


}
