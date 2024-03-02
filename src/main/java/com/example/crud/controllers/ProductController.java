package com.example.crud.controllers;

import com.example.crud.domain.product.Product;
import com.example.crud.domain.product.ProductRepository;
import com.example.crud.domain.product.RequestProductDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository repository;
    @GetMapping
    public ResponseEntity getAllProducts(){
        var allProducts = repository.findAllByActiveTrue();
         return  ResponseEntity.ok(allProducts);
  }

    @PostMapping
    public  ResponseEntity posProduct(@RequestBody @Valid RequestProductDTO data){
        Product newProduct = new Product(data);
         repository.save(newProduct);
         return ResponseEntity.ok().body(newProduct);
    }

    @PutMapping
    @Transactional //quando se executa mais de um comando sql se usa  a anotação @Ttransactional pois  mais de um comendo seria  uma transação
    public  ResponseEntity updateProduct( @RequestBody   RequestProductDTO data){

        Optional<Product> optionalProduct =  repository.findById(data.id());
        if(optionalProduct.isPresent()){
            Product product = optionalProduct.get();
            product.setName(data.name());
            product.setPrice_in_cents(data.price_in_cents());
            return ResponseEntity.ok(product);
        }else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/{id}")
    @Transactional //quando se executa mais de um comando sql se usa  a anotação @Ttransactional pois  mais de um comendo seria  uma transação
    public ResponseEntity deleteProduct(@PathVariable String id){
        Optional<Product> optionalProduct =  repository.findById(id);

        if(optionalProduct.isPresent()){
            Product product = optionalProduct.get();
            product.setActive(false);
            repository.save(product);
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.notFound().build();
        }


    }
}

