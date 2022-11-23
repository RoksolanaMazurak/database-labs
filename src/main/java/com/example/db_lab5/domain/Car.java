package com.example.db_lab5.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
public class Car {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "car_number")
    private String carNumber;

    @Basic
    @Column(name = "car_shop_id")
    private Integer carShop;

    @ManyToOne
    @JoinColumn(name = "car_model_id", referencedColumnName = "id", nullable = false)
    private CarModel carModel;

    @OneToMany(mappedBy = "car")
    private List<Reservation> reservations;

    @ManyToMany(mappedBy = "cars")
    private Set<Driver> drivers;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (id != car.id) return false;
        if (!Objects.equals(carNumber, car.carNumber)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (carNumber != null ? carNumber.hashCode() : 0);
        return result;
    }

    public CarModel getCarModel() {
        return carModel;
    }

    public void setCarModel(CarModel carModel) {
        this.carModel = carModel;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Set<Driver> getDrivers() {
        return drivers;
    }

    public void setPersons(Set<Driver> persons) {
        this.drivers = drivers;
    }

    public Integer getCarShop() {
        return carShop;
    }

    public void setCarShop(Integer carShop) {
        this.carShop = carShop;
    }

}
