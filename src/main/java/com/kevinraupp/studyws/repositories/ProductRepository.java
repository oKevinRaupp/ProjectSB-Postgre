package com.kevinraupp.studyws.repositories;

import com.kevinraupp.studyws.entities.Category;
import com.kevinraupp.studyws.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
