package com.tfliorko.db_lab_5.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "dysney_park", schema = "lab_3", catalog = "")
public class DysneyPark {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "city")
    private String city;
    @Basic
    @Column(name = "street")
    private String street;
    @Basic
    @Column(name = "max_amount_of_visitors")
    private Integer maxAmountOfVisitors;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getMaxAmountOfVisitors() {
        return maxAmountOfVisitors;
    }

    public void setMaxAmountOfVisitors(Integer maxAmountOfVisitors) {
        this.maxAmountOfVisitors = maxAmountOfVisitors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DysneyPark that = (DysneyPark) o;
        return id == that.id && Objects.equals(city, that.city) && Objects.equals(street, that.street) && Objects.equals(maxAmountOfVisitors, that.maxAmountOfVisitors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, city, street, maxAmountOfVisitors);
    }
}
