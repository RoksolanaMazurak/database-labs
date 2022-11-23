package com.example.db_lab5.repository;

import com.example.db_lab5.domain.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

@Repository
public interface CarModelRepository extends JpaRepository<CarModel, Integer> {
    @Procedure("param_insert_car_model")
    CarModel insertParamCarModel(String name, String company, String clazz, Integer seatNum);

    @Query(value = "CALL max_seat_number();", nativeQuery = true)
    Integer getMaxSeatNum();
}
