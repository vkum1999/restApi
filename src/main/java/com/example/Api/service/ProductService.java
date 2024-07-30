package com.example.Api.service;

import com.example.Api.payload.ProductDto;

import java.util.List;

public interface ProductService {



    public ProductDto CreatProduct(ProductDto productDto);

    void deleteProductById(long id);

    ProductDto updateProduct(long id, ProductDto productDto);

    List<ProductDto> GetAllProduct(int pageNo, int pageSize, String sortBy, String sortDir);


    ProductDto getProductById(long id);
}
