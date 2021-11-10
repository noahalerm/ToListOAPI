package com.example.tolistoapi.model.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Llista {
    @Id
    private Long listId;

    private String name;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "idList")
    private List<Task> tasks;
}
