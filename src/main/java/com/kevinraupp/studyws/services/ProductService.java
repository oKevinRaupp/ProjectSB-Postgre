package com.kevinraupp.studyws.services;

import com.kevinraupp.studyws.entities.Product;
import com.kevinraupp.studyws.repositories.ProductRepository;
import com.kevinraupp.studyws.resources.exceptions.DataBaseException;
import com.kevinraupp.studyws.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    private Logger logger = Logger.getLogger(CategoryService.class.getName());

    public List<Product> findAll() {
        logger.info("Finding all users!");
        return productRepository.findAll();
    }

    public Optional<Product> findById(Long id) {
        logger.info("Finding one user!");
        return productRepository.findById(id);
    }

    public Product insert(Product product) {
        logger.info("creating a new product!");
        return productRepository.save(product);
    }

    public Product update(Long id, Product product) {
        try {
            logger.info("Updating product = " + id);
            Product entity = productRepository.findById(id).orElseThrow();
            ;
            updateData(entity, product);
            return productRepository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Product entity, Product product) {
        entity.setName(product.getName());
        entity.setDescription(product.getDescription());
        entity.setImgURL(product.getImgURL());
        entity.setPrice(product.getPrice());
    }

    public void delete(Long id) {
        try {
            if (!productRepository.existsById(id)) throw new ResourceNotFoundException(id);
            logger.info("Deleting a user = " + id);
            productRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException(e.getMessage());
        }
    }
}
