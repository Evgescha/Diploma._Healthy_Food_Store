package com.hescha.healthystore.repository;

import com.hescha.healthystore.model.Category;
import com.hescha.healthystore.model.Comment;
import com.hescha.healthystore.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByName(String name);

    List<Product> findByNameContainsAndDeleted(String name, Boolean deleted);
    List<Product> findByDeleted(Boolean deleted);

    List<Product> findByDescription(String description);

    List<Product> findByDescriptionContains(String description);

    List<Product> findByImage(String image);

    List<Product> findByImageContains(String image);

    Product findByPrice(Double price);

    List<Product>  findByCategory(Category category);

    List<Product> findByCommentsContains(Comment comments);
}
