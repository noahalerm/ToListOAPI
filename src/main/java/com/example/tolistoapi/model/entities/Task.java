package com.example.tolistoapi.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Task {
    @Id
    private Long id;
    private String description;
    private Boolean done;
    private Long position;
    private Long idList;
}
