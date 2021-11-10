package com.example.tolistoapi.model.services;

import com.example.tolistoapi.model.entities.Llista;
import com.example.tolistoapi.model.entities.Task;
import com.example.tolistoapi.model.repositories.ListRepository;
import com.example.tolistoapi.model.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServices {
    //ATTRIBUTES
    @Autowired
    private TaskRepository repository;

    //METHODS
    public List<Task> listTasks(){
        return repository.findAll();
    }

    public Task findTask(Long id){
        return repository.findById(id).orElse(null);
    }

    public Task addTask(Task it){
        return repository.save(it);
    }

    public Task modifyTask(Task it){
        Task aux = null;
        if(repository.existsById(it.getId())) aux = repository.save(it);
        return aux;
    }

    public Task deleteTask(Long id){
        Task res = repository.findById(id).orElse(null);
        if(res != null) repository.deleteById(id);
        return res;
    }
}
