package com.hescha.healthystore.service;

import com.hescha.healthystore.model.Category;
import com.hescha.healthystore.model.Product;
import com.hescha.healthystore.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService extends CrudService<Category> {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public List<Category> findByName(String name) {
        return repository.findByName(name);
    }

    public List<Category> findByNameContains(String name) {
        return repository.findByNameContains(name);
    }

    public List<Category> findByProductsContains(Product products) {
        return repository.findByProductsContains(products);
    }


    public Category update(Long id, Category entity) {
        Category read = read(id);
        if (read == null) {
            throw new RuntimeException("Entity Category not found");
        }
        updateFields(entity, read);
        return update(read);

    }

    private void updateFields(Category entity, Category read) {
        read.setName(entity.getName());
        read.setProducts(entity.getProducts());
    }
}
