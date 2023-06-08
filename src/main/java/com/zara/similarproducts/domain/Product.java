package com.zara.similarproducts.domain;

import lombok.Data;


@Data
public class Product {
    private String id;
    private String name;
    private Double price;
    private String availability;
}
