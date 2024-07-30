package com.example.Api.payload;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ProductDto {


    private Long id;

 @Size(min=2,max =20,message = "should be more then 2 characters")
    private String productname;


    private String productprice;




    private String productcolour;


    private String productlength;

    private  String message;

}