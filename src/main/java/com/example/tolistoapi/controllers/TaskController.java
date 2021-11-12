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

    /**
     * TaskController Constructor
     * @param services Task services
     */
    @Autowired
    public TaskController(TaskServices services) {
        this.services = services;
    }

    //METHODS
    /**
     * This method is used to show all tasks from a given list.
     * @param idList List that contains the tasks
     * @return 404 or 200 OK
     */
    @GetMapping("/lists/{idList}/tasks")
    public ResponseEntity<?> listTasks(@PathVariable Llista idList){
        List<Task> tasks = idList.getTasks();

        if (tasks == null) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(tasks);
    }

    /**
     * This method is used to show a specific task.
     * @param idList List that contains the task
     * @param id Task's ID
     * @return 404 or 200 OK
     */
    @GetMapping("/lists/{idList}/tasks/{id}")
    public ResponseEntity<?> findTask(@PathVariable Llista idList, Task id) {
        Task task = services.findTask(id.getId());

        if (task == null) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(task);
    }

    @PostMapping("/lists/{idList}/tasks")
    public ResponseEntity<?> createTask(@RequestBody Task newTask, @PathVariable Llista idList){
        idList.getTasks().add(newTask);
        newTask.setIdList(idList.getListId());
        Task task = services.addTask(newTask);

        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    @DeleteMapping("/lists/{idList}/tasks/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Llista idList, Task id){
        Task task = services.deleteTask(id);

        if (task == null) return ResponseEntity.notFound().build();
        else return new ResponseEntity<>(task, HttpStatus.NO_CONTENT);
    }

    @PutMapping("/lists/{idList}/tasks/{id}")
    public ResponseEntity<?> modifyTask(@PathVariable Llista idList, Task id){
        Task task = services.modifyTask(id);

        if (task == null) return ResponseEntity.notFound().build();
        return new ResponseEntity<>(task, HttpStatus.OK);
    }
}
