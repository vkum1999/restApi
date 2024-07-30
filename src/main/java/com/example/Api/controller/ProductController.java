package com.example.Api.controller;



import com.example.Api.entity.Product;
import com.example.Api.payload.ProductDto;
import com.example.Api.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PostMapping
    public ResponseEntity<?> getProduct(
           @Valid @RequestBody ProductDto productDto,
           BindingResult result
           ){
        if(result.hasErrors()){
            return  new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.OK);
        }
    ProductDto pdt= productService.CreatProduct(productDto);
        return new ResponseEntity<>(pdt,HttpStatus.CREATED);

    }
//    http://localhost:8080/api/v1/product?id=
    @DeleteMapping
    public  ResponseEntity<String>deleteProductById( @RequestParam long id){
          productService.deleteProductById(id);
          return new ResponseEntity<>("product deleted",HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ProductDto>UpdateProduct(
            @Valid @RequestParam long id, @RequestBody ProductDto productDto,
            BindingResult result
    ){

      ProductDto dto=  productService.updateProduct(id,productDto);
      return  new ResponseEntity<>(dto,HttpStatus.OK);

    }
//    http://localhost:8080/api/v1/product?pageNo=0&pageSize=5&sortBy=email
    @GetMapping()
    public ResponseEntity<List<ProductDto>>GetAllProduct(
            @RequestParam(name="pageNo",defaultValue = "0",required = false) int pageNo,
            @RequestParam(name="pageSize",defaultValue = "3",required = false) int pageSize,
            @RequestParam(name="sortBy",defaultValue = "name",required = false)String sortBy,
            @RequestParam(name="sortDir",defaultValue = "name",required = false)String sortDir
    ){

        List<ProductDto> Dtos = productService.GetAllProduct(pageNo,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(Dtos,HttpStatus.OK);
    }
    @GetMapping("byId")
public ResponseEntity<ProductDto>getProductById(
        @RequestParam long id
){
  ProductDto dto =   productService.getProductById(id);
   return  new ResponseEntity<>(dto,HttpStatus.OK);
}



}
