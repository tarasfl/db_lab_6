package com.tfliorko.db_lab_5.domain;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "`show`", schema = "lab_3", catalog = "")
public class Show {
    @Basic
    @Column(name = "name")
    private String name;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "date")
    private Date date;
    @Basic
    @Column(name = "max_amount_of_visitors")
    private int maxAmountOfVisitors;
    @OneToOne
    @JoinColumn(name = "dysney_park_id", referencedColumnName = "id", nullable = false)
    private DysneyPark dysneyPark;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
        Show that = (Show) o;
        return id == that.id && maxAmountOfVisitors == that.maxAmountOfVisitors && Objects.equals(name, that.name) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, date, maxAmountOfVisitors);
    }

    public DysneyPark getDysneyPark() {
        return dysneyPark;
    }

    public void setDysneyPark(DysneyPark dysneyPark) {
        this.dysneyPark = dysneyPark;
    }
}
