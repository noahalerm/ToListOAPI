package com.example.tolistoapi.controllers;

import com.example.tolistoapi.model.entities.Task;
import com.example.tolistoapi.model.services.TaskServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TaskController {
    //ATTRIBUTES
    private TaskServices services;

    //METHODS
    @GetMapping("/tasks")
    public List<Task> listTasks(){
        return services.listTasks();
    }

    @GetMapping("/tasks/{id}")
    public Task findTask(@PathVariable Long id) {
        return services.findTask(id);
    }

    @PostMapping("/tasks")
    public Task createTask(@RequestBody Task newTask){
        return services.addTask(newTask);
    }

    @DeleteMapping("/tasks/{id}")
    public Task deleteTask(@PathVariable Long id){
        return services.deleteTask(id);
    }

    @PutMapping("/tasks")
    public Task modifyTask(@RequestBody Task mod){
        return services.modifyTask(mod);
    }
}
