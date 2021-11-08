package com.example.tolistoapi.model.repositories;

import com.example.tolistoapi.model.entities.Llista;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListRepository extends JpaRepository<Llista, Long> {
}
