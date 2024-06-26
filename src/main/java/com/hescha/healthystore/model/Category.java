package com.hescha.healthystore.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Category extends AbstractEntity{
    private String name;
    @OneToMany
    private List<Product> products = new ArrayList<>();
}
