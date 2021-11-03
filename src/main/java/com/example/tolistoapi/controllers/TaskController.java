package com.example.tolistoapi.controllers;

import com.example.tolistoapi.model.entities.Task;
import com.example.tolistoapi.model.services.TaskServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class TaskController {
    //ATTRIBUTES
    @Autowired
    private TaskServices services;

    //METHODS
    @GetMapping("/tasks")
    public ResponseEntity<?> listTasks(){
        List<Task> task = services.listTasks();
        if (task == null) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(task);
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<?> findTask(@PathVariable Long id) {
        Task task = services.findTask(id);
        if (task == null) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(task);
    }

    @PostMapping("/tasks")
    public ResponseEntity<?> createTask(@RequestBody Task newTask){
        Task task = services.addTask(newTask);
        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id){
        Task task = services.deleteTask(id);
        if (task == null) return null;
        else return new ResponseEntity<>(task, HttpStatus.NO_CONTENT);
    }

    @PutMapping("/tasks")
    public ResponseEntity<?> modifyTask(@RequestBody Task mod){
        Task task = services.modifyTask(mod);
        if (task == null) return ResponseEntity.notFound().build();
        return new ResponseEntity<>(task, HttpStatus.OK);
    }
}
