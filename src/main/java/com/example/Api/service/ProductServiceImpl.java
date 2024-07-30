package com.example.Api.service;

import com.example.Api.entity.Product;
import com.example.Api.expection.ResourceNotFound;
import com.example.Api.payload.ProductDto;
import com.example.Api.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
class ProductServiceImpl implements  ProductService{
 private  ProductRepository productRepository;



    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }



    @Override
    public ProductDto CreatProduct(ProductDto productDto) {
        Product product = maptoEntity(productDto);
        Product saveEntity = productRepository.save(product);
        ProductDto Dto1 = maptoDto(saveEntity);
        Dto1.setMessage("this is data is saved");
        return  Dto1;

    }

    @Override
    public void deleteProductById(long id) {
        productRepository.deleteById(id);

    }

    @Override
    public ProductDto updateProduct(long id, ProductDto productDto) {
        Optional<Product> opPro = productRepository.findById(id);
        Product product = opPro.get();
        product.setProductname(productDto.getProductname());
        product.setProductprice(productDto.getProductprice());
        product.setProductcolour(productDto.getProductcolour());
        product.setProductlength(productDto.getProductlength());
        Product saveEntity = productRepository.save(product);
        ProductDto Dto = maptoDto(product);

        return Dto;
    }

    @Override
    public List<ProductDto> GetAllProduct(int pageNo, int pageSize, String sortBy, String sortDir) {
//        List<Product> reg = productRepository.findAll();
        Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(Sort.Direction.ASC,sortBy):
                Sort.by(Sort.Direction.DESC,sortBy);
     Pageable pageable= PageRequest.of(pageNo,pageSize, sort);
        Page<Product> all = productRepository.findAll(pageable);
        List<Product> products = all.getContent();
        List<ProductDto> productDtos = products.stream().map(p -> maptoDto(p)).collect(Collectors.toList());
        System.out.println(all.getTotalPages());
        System.out.println(all.isLast());
        System.out.println(all.isFirst());
        System.out.println(pageable.getPageSize());
        System.out.println(pageable.getPageNumber());
        return productDtos;
    }

    @Override
    public ProductDto getProductById(long id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Product not found with id :=" + id)
        );
        ProductDto productDto = maptoDto(product);
        return productDto;
    }


    Product maptoEntity(ProductDto dto){
     Product entiy=new Product();
     entiy.setProductname(dto.getProductname());
     entiy.setProductprice(dto.getProductprice());
     entiy.setProductcolour(dto.getProductcolour());
     entiy.setProductlength(dto.getProductlength());
     return  entiy;
    }

    ProductDto maptoDto(Product product){
        ProductDto dto=new ProductDto();
        dto.setId(dto.getId());
        dto.setProductname(product.getProductname());
        dto.setProductprice(product.getProductprice());
        dto.setProductcolour(product.getProductcolour());
        dto.setProductlength(product.getProductlength());
        return  dto;
    }
}
