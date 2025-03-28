package com.thinkify.flipkartdaily.model;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Item {
    private String id;
    private String name;
    private int quantity;
    private double price;
    private String category;
    private String brand;
    private String description;
    private Date createdAt;
    private Date updatedAt;
}