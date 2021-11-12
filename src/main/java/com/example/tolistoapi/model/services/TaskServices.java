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

    //CONSTRUCTOR
    /**
     * TaskServices Constructor
     * @param repository Task repository containing different queries to the database.
     */
    @Autowired
    public TaskServices(TaskRepository repository) {
        this.repository = repository;
    }

    //METHODS
    /**
     * This method is used to get all the tasks in the database.
     * @return task list (List)
     * @deprecated The method is currently not used, as we need to show the tasks from a given list.
     */
    public List<Task> listTasks(){
        return repository.findAll();
    }

    /**
     * This method is used to show a given task.
     * @param id Task's ID
     * @return task (Task)
     */
    public Task findTask(Long id){
        return repository.findById(id).orElse(null);
    }

    /**
     * This method is used to add a new task to the database.
     * @param it New Task
     * @return new task (Task)
     */
    public Task addTask(Task it){
        return repository.save(it);
    }

    /**
     * This method is to set a given task as done.
     * @param it Task
     * @return updated task (Task)
     */
    public Task modifyTask(Task it) {
        Task aux = null;

        //These lines make sure to keep all the task's attributes the same and to change the done boolean.
        it = repository.findById(it.getId()).orElse(null);
        it.setDone(!it.getDone());

        //If the task exits it is saved.
        if(repository.existsById(it.getId())) aux = repository.save(it);
        return aux;
    }

    /**
     * This method is used to delete a given task.
     * @param task Task to be deleted
     * @return deleted task (Task)
     */
    public Task deleteTask(Task task){
        Task res = repository.findById(task.getId()).orElse(null);

        //If the task isn't null, it gets deleted.
        if(res != null) repository.deleteById(task.getId());
        return res;
    }
}
