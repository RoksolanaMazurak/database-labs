package com.example.db_lab5.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class CarShop {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "name")
    private String carShopName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCarShopName() {
        return carShopName;
    }

    public void setCarShopName(String carShopName) {
        this.carShopName = carShopName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarShop carShop = (CarShop) o;
        return id == carShop.id && Objects.equals(carShopName, carShop.carShopName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, carShopName);
    }
}
