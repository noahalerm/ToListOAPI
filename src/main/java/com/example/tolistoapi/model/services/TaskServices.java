package com.example.tolistoapi.model.services;

import com.example.tolistoapi.model.entities.Task;
import com.example.tolistoapi.model.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServices {
    //ATTRIBUTES
    private TaskRepository repository;

    @Autowired
    public TaskServices(TaskRepository repository) {
        this.repository = repository;
    }

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

    public Task modifyTask(Task it) {
        Task aux = null;
        it = repository.findById(it.getId()).orElse(null);
        it.setDone(!it.getDone());

        if(repository.existsById(it.getId())) aux = repository.save(it);
        return aux;
    }

    public Task deleteTask(Task task){
        Task res = repository.findById(task.getId()).orElse(null);
        if(res != null) repository.deleteById(task.getId());
        return res;
    }
}
