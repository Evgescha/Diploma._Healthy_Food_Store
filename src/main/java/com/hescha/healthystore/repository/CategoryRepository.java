package com.hescha.healthystore.repository;

import com.hescha.healthystore.model.Category;
import com.hescha.healthystore.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByName(String name);

    List<Category> findByNameContains(String name);

    List<Category> findByProductsContains(Product products);
}
