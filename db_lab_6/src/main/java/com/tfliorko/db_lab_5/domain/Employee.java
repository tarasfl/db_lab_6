package com.tfliorko.db_lab_5.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "employee", schema = "lab_3", catalog = "")
public class Employee {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "first_name")
    private String firstName;
    @Basic
    @Column(name = "second_name")
    private String secondName;
    @Basic
    @Column(name = "type_of_job")
    private String typeOfJob;
    @OneToOne
    @JoinColumn(name = "Attraction_id", referencedColumnName = "id", nullable = false)
    private Attraction attraction;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getTypeOfJob() {
        return typeOfJob;
    }

    public void setTypeOfJob(String typeOfJob) {
        this.typeOfJob = typeOfJob;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee that = (Employee) o;
        return id == that.id && Objects.equals(firstName, that.firstName) && Objects.equals(secondName, that.secondName) && Objects.equals(typeOfJob, that.typeOfJob);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, secondName, typeOfJob);
    }

    public Attraction getAttraction() {
        return attraction;
    }

    public void setAttraction(Attraction attraction) {
        this.attraction = attraction;
    }
}
