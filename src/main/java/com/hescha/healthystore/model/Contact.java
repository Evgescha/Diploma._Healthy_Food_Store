package com.hescha.healthystore.model;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class Contact extends AbstractEntity {
    private String name;
    private String email;
    private String subject;
    private String message;
    private ContactStatus contactStatus = ContactStatus.NEW;
}
