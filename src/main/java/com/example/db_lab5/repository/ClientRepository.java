package com.example.db_lab5.repository;

import com.example.db_lab5.domain.CarModel;
import com.example.db_lab5.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    @Procedure("cursor_create_tables_by_client_name")
    void createTablesByCursor();
}
