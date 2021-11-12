package com.example.tolistoapi.model.services;

import com.example.tolistoapi.model.entities.Llista;
import com.example.tolistoapi.model.entities.Task;
import com.example.tolistoapi.model.repositories.ListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListServices {
    //ATTRIBUTES
    private ListRepository repository;
    private TaskServices taskServices;

    @Autowired
    public ListServices(ListRepository repository, TaskServices services) {
        this.repository = repository;
        this.taskServices = services;
    }

    //METHODS
    public List<Llista> listLists() {
        return repository.findAll();
    }

    public Llista addList(Llista it){
        return repository.save(it);
    }

    public Llista modifyList(Llista it){
        Llista aux = null;
        if(repository.existsById(it.getListId())) aux = repository.save(it);
        return aux;
    }

    public Llista deleteList(Long id){
        Llista res = repository.findById(id).orElse(null);

        List<Task> tasks = res.getTasks();

        for (Task task : tasks) {
            taskServices.deleteTask(task);
        }

        if(res != null) repository.deleteById(id);
        return res;
    }
}
