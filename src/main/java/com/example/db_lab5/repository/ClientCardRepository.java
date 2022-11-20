package com.example.db_lab5.repository;

import com.example.db_lab5.domain.ClientCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientCardRepository extends JpaRepository<ClientCard, Integer> {
}
