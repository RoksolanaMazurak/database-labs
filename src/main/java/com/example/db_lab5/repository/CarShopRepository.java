package com.example.db_lab5.repository;

import com.example.db_lab5.domain.CarShop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarShopRepository extends JpaRepository<CarShop, Integer> {
}