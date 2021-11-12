package com.example.tolistoapi.model.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * This class stores each list's data, including a foreign key that contains all tasks which pertain to this list.
 */
@Data
@Entity
public class Llista {
    @Id
    private Long listId;

    //ATTRIBUTES
    private String name;

    //FOREIGN KEY
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "idList")
    private List<Task> tasks;
}
