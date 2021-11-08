package com.example.tolistoapi.model.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Llista {
    @Id
    private Long listId;

    private String name;
}
