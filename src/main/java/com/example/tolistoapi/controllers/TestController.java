package com.example.tolistoapi.controllers;

import com.example.tolistoapi.model.entities.Task;
import com.example.tolistoapi.model.services.TaskServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {
    private final TaskServices taskServices;

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/lists/{idList}")
    public ResponseEntity<?> findTask(@PathVariable Long idList) {
        Task tas = taskServices.findTask(idList);
        if (tas == null) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(tas);
    }
}
