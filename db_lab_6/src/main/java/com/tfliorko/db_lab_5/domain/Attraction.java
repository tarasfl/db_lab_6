package com.tfliorko.db_lab_5.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "attraction", schema = "lab_3", catalog = "")
public class Attraction {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "priority_pass")
    private Byte priorityPass;
    @Basic
    @Column(name = "max_amount_of_visitors")
    private int maxAmountOfVisitors;
    @OneToOne
    @JoinColumn(name = "dysney_park_id", referencedColumnName = "id", nullable = false)
    private DysneyPark dysneyPark;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getPriorityPass() {
        return priorityPass;
    }

    public void setPriorityPass(Byte priorityPass) {
        this.priorityPass = priorityPass;
    }

    public int getMaxAmountOfVisitors() {
        return maxAmountOfVisitors;
    }

    public void setMaxAmountOfVisitors(int maxAmountOfVisitors) {
        this.maxAmountOfVisitors = maxAmountOfVisitors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attraction that = (Attraction) o;
        return id == that.id && maxAmountOfVisitors == that.maxAmountOfVisitors && Objects.equals(name, that.name) && Objects.equals(priorityPass, that.priorityPass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, priorityPass, maxAmountOfVisitors);
    }

    public DysneyPark getDysneyPark() {
        return dysneyPark;
    }

    public void setDysneyPark(DysneyPark dysneyPark) {
        this.dysneyPark = dysneyPark;
    }
}
