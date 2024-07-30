package com.example.Api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "product")

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "productname", nullable = false, length = 20)
    private String productname;

    @Column(name = "productprice", nullable = false, length = 16)
    private String productprice;

    @Column(name = "productcolour", nullable = false, length = 15)
    private String productcolour;

    @Column(name = "productlength", nullable = false, length = 18)
    private String productlength;

}