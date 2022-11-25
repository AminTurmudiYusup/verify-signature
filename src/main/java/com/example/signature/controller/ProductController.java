package com.example.signature.controller;

import com.example.signature.entity.Product;
import com.example.signature.repository.ProductRepsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {
    @Autowired
    private ProductRepsitory productRepository;

    @GetMapping("/product")//api for handle request to get all product
    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }

    @GetMapping("/product/{id}")//api fro handle request to get spesific product
    public Optional<Product> getProduct(@PathVariable("id") Long id){
        return productRepository.findById(id);
    }

    @PostMapping("/product")//api for handle request for save product
    public Product addProduct(@RequestBody Product product){
        return productRepository.save(product);
    }

    @PutMapping("/product")//api for handle request to update product
    public Product editProduct(@RequestBody Product newProduct) throws Exception {
        String s = "Not FOund";
        return productRepository.findById(newProduct.getId())
                .map(product -> {
                    if (product!=null){
                        product.setProductName(newProduct.getProductName());
                        product.setExpiredDate(newProduct.getExpiredDate());
                        product.setStock(newProduct.getStock());
                        return productRepository.save(product);
                    }
                    return newProduct;
                }).orElseThrow(()-> new  Exception("Not Found"));
    }

    @DeleteMapping("/product/{id}")//api for handle request to delete product
    public void deleteProduct(@PathVariable("id")Long id){
        productRepository.deleteById(id);
    }
}
