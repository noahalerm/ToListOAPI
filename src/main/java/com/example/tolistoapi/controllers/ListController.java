package com.example.tolistoapi.controllers;

import com.example.tolistoapi.model.entities.Llista;
import com.example.tolistoapi.model.services.ListServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
public class ListController {
    //ATTRIBUTES
    private ListServices services;

    /**
     * ListController Constructor
     * @param services List services
     */
    @Autowired
    public ListController(ListServices services) {
        this.services = services;
    }

    //METHODS
    /**
     * This method is used to show all lists.
     * @return 404 Not found or 200 OK
     */
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/lists")
    public ResponseEntity<?> listLists(){
        List<Llista> list = services.listLists();

        if (list == null) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(list);
    }

    /**
     * This method is used to create a new list.
     * @param newList New List (LLISTA)
     * @return 201 Created
     */
    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/lists")
    public ResponseEntity<?> createList(@RequestBody Llista newList){
        Llista list = services.addList(newList);

        return new ResponseEntity<>(list, HttpStatus.CREATED);
    }

    /**
     * This method is used to delete a given list.
     * @param id List's ID (LONG)
     * @return 404 Not Found or 204 No Content
     */
    @CrossOrigin(origins = "http://localhost:8080")
    @DeleteMapping("/lists/{id}")
    public ResponseEntity<?> deleteList(@PathVariable Long id){
        Llista list = services.deleteList(id);

        if (list == null) return ResponseEntity.notFound().build();
        else return new ResponseEntity<>(list, HttpStatus.NO_CONTENT);
    }

    /**
     * This method is used to modify a given lists name.
     * @param mod Modified List (LLISTA)
     * @param id List's ID (LLISTA)
     * @return 404 Not Found or 200 OK
     */
    @CrossOrigin(origins = "http://localhost:8080")
    @PutMapping("/lists/{id}")
    public ResponseEntity<?> modifyList(@RequestBody Llista mod, @PathVariable Llista id){
        Llista list = null;

        //Adding the task list to the modified list.
        mod.setTasks(id.getTasks());

        //If both lists have the same ID, the list is modified.
        if (Objects.equals(mod.getListId(), id.getListId()))
            list = services.modifyList(mod);

        if (list == null) return ResponseEntity.notFound().build();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
