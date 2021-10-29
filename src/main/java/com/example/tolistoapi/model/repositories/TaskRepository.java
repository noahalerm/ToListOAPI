package com.example.tolistoapi.model.repositories;

import com.example.tolistoapi.model.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
