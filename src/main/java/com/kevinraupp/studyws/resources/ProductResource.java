package com.kevinraupp.studyws.resources;

import com.kevinraupp.studyws.entities.Category;
import com.kevinraupp.studyws.entities.Product;
import com.kevinraupp.studyws.services.CategoryService;
import com.kevinraupp.studyws.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/products")
public class ProductResource {
    @Autowired
    private ProductService productService;
    @GetMapping
    public ResponseEntity<List<Product>> findAll(){
        List<Product> list = productService.finall();
        return ResponseEntity.ok(list);
    }
    @GetMapping(value = "/{id}")
    public Optional<Product> findById(@PathVariable Long id){
        return productService.findById(id);
    }

}
