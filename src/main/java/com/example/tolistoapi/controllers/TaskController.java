package com.example.tolistoapi.controllers;

import com.example.tolistoapi.model.entities.Llista;
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
    private TaskServices services;

    @Autowired
    public TaskController(TaskServices services) {
        this.services = services;
    }

    //METHODS
    @GetMapping("/lists/{idList}/tasks")
    public ResponseEntity<?> listTasks(@PathVariable Llista idList){
        List<Task> tasks = services.listTasks();

        //tasks = idList.getTasks();

        if (tasks == null) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(tasks);
    }

    @GetMapping("/lists/{idList}/tasks/{id}")
    public ResponseEntity<?> findTask(@PathVariable Long idList, Long id) {
        Task task = services.findTask(id);
        if (task == null) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(task);
    }

    @PostMapping("/lists/{idList}/tasks")
    public ResponseEntity<?> createTask(@RequestBody Task newTask, @PathVariable Long idList){
        Task task = services.addTask(newTask);
        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    @DeleteMapping("/lists/{idList}/tasks/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Llista idList, Task id){
        Task task = services.deleteTask(id);
        if (task == null) return ResponseEntity.notFound().build();
        else return new ResponseEntity<>(task, HttpStatus.NO_CONTENT);
    }

    @PutMapping("/lists/{idList}/tasks")
    public ResponseEntity<?> modifyTask(@RequestBody Task mod, @PathVariable Long idList){
        Task task = services.modifyTask(mod);
        if (task == null) return ResponseEntity.notFound().build();
        return new ResponseEntity<>(task, HttpStatus.OK);
    }
}
