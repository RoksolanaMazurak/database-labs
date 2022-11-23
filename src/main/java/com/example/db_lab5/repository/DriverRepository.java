package com.example.db_lab5.repository;

import com.example.db_lab5.domain.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Integer> {
    @Procedure("insert_drivers")
    void insertDrivers();

    @Procedure("connect_driver_and_car")
    void connectDriverAndCar(String driverSurname, String carNum);
}