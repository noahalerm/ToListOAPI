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
import java.util.Objects;


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
     * @param idList List that contains the tasks (LLISTA)
     * @return 404 Not Found or 200 OK
     */
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/lists/{idList}/tasks")
    public ResponseEntity<?> listTasks(@PathVariable Llista idList){
        List<Task> tasks = idList.getTasks();

        if (tasks == null) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(tasks);
    }

    /**
     * This method is used to show a specific task.
     * @param idList List that contains the task (LLISTA)
     * @param id Task's ID (TASK)
     * @return 404 Not Found or 200 OK
     */
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/lists/{idList}/tasks/{id}")
    public ResponseEntity<?> findTask(@PathVariable Llista idList, Task id) {
        Task task = null;

        //If the task is located on the given list it is shown.
        if (idList.getTasks().stream().anyMatch(t -> Objects.equals(t.getId(), id.getId())))
            task = services.findTask(id.getId());

        if (task == null) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(task);
    }

    /**
     * This method is used to insert a new task on a given list.
     * @param newTask New Task (TASK)
     * @param idList List where the task will be inserted (LLISTA)
     * @return 201 Created
     */
    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/lists/{idList}/tasks")
    public ResponseEntity<?> createTask(@RequestBody Task newTask, @PathVariable Llista idList){
        //These two lines create a connection between the list and the new task.
        idList.getTasks().add(newTask);
        newTask.setIdList(idList.getListId());

        Task task = services.addTask(newTask);

        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    /**
     * This method is used to delete a given task.
     * @param idList List that contains the task (LLISTA)
     * @param id Task's ID (TASK)
     * @return 404 Not Found or 204 No Content
     */
    @CrossOrigin(origins = "http://localhost:8080")
    @DeleteMapping("/lists/{idList}/tasks/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Llista idList, Task id){
        Task task = null;

        //POSITION UPDATE
        /*for (Task t: idList.getTasks()) {
            if (t.getPosition() > id.getPosition())
                t.setPosition(t.getPosition()-1);
        }*/

        //If the task is located on the given list it is deleted.
        if (idList.getTasks().stream().anyMatch(t -> Objects.equals(t.getId(), id.getId())))
            task = services.deleteTask(id);

        if (task == null) return ResponseEntity.notFound().build();
        else return new ResponseEntity<>(task, HttpStatus.NO_CONTENT);
    }

    /**
     * This method is used to set a given task as done.
     * @param idList List that contains the task (LLISTA)
     * @param id Task's ID (TASK)
     * @return 404 Not Found or 200 OK
     */
    @CrossOrigin(origins = "http://localhost:8080")
    @PutMapping("/lists/{idList}/tasks/{id}")
    public ResponseEntity<?> modifyTask(@PathVariable Llista idList, Task id){
        Task task = null;

        //If the task is located on the given list it's set as done.
        if (idList.getTasks().stream().anyMatch(t -> Objects.equals(t.getId(), id.getId())))
            task = services.modifyTask(id);

        if (task == null) return ResponseEntity.notFound().build();
        return new ResponseEntity<>(task, HttpStatus.OK);
    }
}
