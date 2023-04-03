package com.hescha.healthystore.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Data
public class Comment extends AbstractEntity {
    private String message;
    @ManyToOne
    private User owner;
    @ManyToOne
    private Product product;
}
