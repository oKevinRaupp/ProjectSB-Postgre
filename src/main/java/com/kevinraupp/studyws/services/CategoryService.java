package com.kevinraupp.studyws.services;

import com.kevinraupp.studyws.entities.Category;
import com.kevinraupp.studyws.repositories.CategoryRepository;
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
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    private Logger logger = Logger.getLogger(CategoryService.class.getName());

    public List<Category> finall() {
        logger.info("Finding all categories!");
        return categoryRepository.findAll();
    }

    public Optional<Category> findById(Long id) {
        logger.info("Finding one category!");
        return categoryRepository.findById(id);
    }

    public Category insert(Category category) {
        logger.info("creating a new category!");
        return categoryRepository.save(category);
    }

    public Category update(Long id, Category category) {
        try {
            logger.info("Updating category = " + id);
            Category entity;
            entity = categoryRepository.findById(id).orElseThrow();
            entity.setName(category.getName());
            return categoryRepository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    public void delete(Long id, Category category) {
        try {
            if (!categoryRepository.existsById(id)) throw new ResourceNotFoundException(id);
            if (!category.getProducts().isEmpty()) {
                logger.info("Deleting a category with products! " + category.getProducts().toString());
                throw new DataBaseException("Deleting a category with products!");
            } else {
                logger.info("Deleting a category = " + id);
                categoryRepository.deleteById(id);
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException(e.getMessage());
        }
    }
}
