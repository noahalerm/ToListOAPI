package com.example.tolistoapi.model.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Task {
    @Id
    private Long id;
    private String description;
    private Boolean done;
    private Long position;
}
