package com.kevinraupp.studyws.services;

import com.kevinraupp.studyws.entities.Category;
import com.kevinraupp.studyws.entities.Product;
import com.kevinraupp.studyws.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> finall(){
        return productRepository.findAll();
    }

    public Optional<Product> findById(Long id){
        return productRepository.findById(id);
    }


}
