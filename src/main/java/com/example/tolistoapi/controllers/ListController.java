package com.example.tolistoapi.controllers;

import com.example.tolistoapi.model.entities.Llista;
import com.example.tolistoapi.model.services.ListServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ListController {
    //ATTRIBUTES
    private ListServices services;

    @Autowired
    public ListController(ListServices services) {
        this.services = services;
    }

    //METHODS
    @GetMapping("/lists")
    public ResponseEntity<?> listLists(){
        List<Llista> list = services.listLists();
        if (list == null) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(list);
    }

    @PostMapping("/lists")
    public ResponseEntity<?> createList(@RequestBody Llista newList){
        Llista list = services.addList(newList);
        return new ResponseEntity<>(list, HttpStatus.CREATED);
    }

    @DeleteMapping("/lists/{id}")
    public ResponseEntity<?> deleteList(@PathVariable Long id){
        Llista list = services.deleteList(id);
        if (list == null) return ResponseEntity.notFound().build();
        else return new ResponseEntity<>(list, HttpStatus.NO_CONTENT);
    }

    @PutMapping("/lists")
    public ResponseEntity<?> modifyList(@RequestBody Llista mod){
        Llista list = services.modifyList(mod);
        if (list == null) return ResponseEntity.notFound().build();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
