package com.example.db_lab5.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "driver", schema = "mazurak", catalog = "")

public class Driver {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "surname")
    private String surname;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "gender")
    private String gender;
    @ManyToOne
    @JoinColumn(name = "rating_id", referencedColumnName = "id", nullable = false)
    private Rating rating;

    @ManyToMany
    @JoinTable(name = "driver_has_car", schema = "mazurak",
            joinColumns = @JoinColumn(name = "driver_id", referencedColumnName = "id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "car_id", referencedColumnName = "id", nullable = false))
    private Set<Car> cars;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Driver that = (Driver) o;

        if (id != that.id) return false;
        if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (gender != null ? !gender.equals(that.gender) : that.gender != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        return result;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }
}
