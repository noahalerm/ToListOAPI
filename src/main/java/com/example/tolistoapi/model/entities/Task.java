package com.example.tolistoapi.model.entities;

import lombok.Data;

import javax.persistence.*;

/**
 * This class is used to store each task's data.
 */
@Data
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //ATTRIBUTES
    private String description;
    private Boolean done;
    private Long position;
    //This attribute is used to check to which list pertains the task.
    private Long idList;
}
