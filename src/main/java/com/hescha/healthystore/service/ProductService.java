package com.hescha.healthystore.service;

import com.hescha.healthystore.model.Category;
import com.hescha.healthystore.model.Comment;
import com.hescha.healthystore.model.Product;
import com.hescha.healthystore.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService extends CrudService<Product> {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public List<Product> findByName(String name) {
        return repository.findByName(name);
    }

    public List<Product> findByNameContains(String name) {
        return repository.findByNameContains(name);
    }

    public List<Product> findByDescription(String description) {
        return repository.findByDescription(description);
    }

    public List<Product> findByDescriptionContains(String description) {
        return repository.findByDescriptionContains(description);
    }

    public List<Product> findByImage(String image) {
        return repository.findByImage(image);
    }

    public List<Product> findByImageContains(String image) {
        return repository.findByImageContains(image);
    }

    public Product findByPrice(Double price) {
        return repository.findByPrice(price);
    }

    public Product findByCategory(Category category) {
        return repository.findByCategory(category);
    }

    public List<Product> findByCommentsContains(Comment comments) {
        return repository.findByCommentsContains(comments);
    }


    public Product update(Long id, Product entity) {
        Product read = read(id);
        if (read == null) {
            throw new RuntimeException("Entity Product not found");
        }
        updateFields(entity, read);
        return update(read);

    }

    private void updateFields(Product entity, Product read) {
        read.setName(entity.getName());
        read.setDescription(entity.getDescription());
        read.setImage(entity.getImage());
        read.setPrice(entity.getPrice());
        read.setCategory(entity.getCategory());
        read.setComments(entity.getComments());
    }
}
