package com.hescha.healthystore.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Product extends AbstractEntity{
    private String name;
    @Column(length = 3500)
    private String description;
    private String image;
    private Double price;
    @ManyToOne
    private Category category;
    @OneToMany
    private List<Comment> comments = new ArrayList<>();
    private Boolean deleted = false;
}
