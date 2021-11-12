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

    /**
     * ListServices Constructor
     * @param repository List repository
     * @param services Task services
     */
    @Autowired
    public ListServices(ListRepository repository, TaskServices services) {
        this.repository = repository;
        this.taskServices = services;
    }

    //METHODS
    /**
     * This method shows all lists in the database.
     * @return list of lists (List)
     */
    public List<Llista> listLists() {
        return repository.findAll();
    }

    /**
     * This method is used to add a new list to the database.
     * @param it New List
     * @return new list (Llista)
     */
    public Llista addList(Llista it){
        return repository.save(it);
    }

    /**
     * This method is used to modify a list.
     * @param it List to be updated
     * @return updated list (Llista)
     */
    public Llista modifyList(Llista it){
        Llista aux = null;

        //If the list exists, it's updated.
        if(repository.existsById(it.getListId())) aux = repository.save(it);
        return aux;
    }

    /**
     * This method is used to delete a given list.
     * @param id ID of the list to be deleted
     * @return deleted list (Llista)
     */
    public Llista deleteList(Long id){
        Llista res = repository.findById(id).orElse(null);

        //If the list isn't null, it's deleted.
        if(res != null) {
            List<Task> tasks = res.getTasks();

            //This loop deletes all tasks in the list.
            for (Task task : tasks) {
                taskServices.deleteTask(task);
            }

            repository.deleteById(id);
        }
        return res;
    }
}
