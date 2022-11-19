package com.tfliorko.db_lab_5.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "tickets", schema = "lab_3", catalog = "")
public class Tickets {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "price")
    private BigDecimal price;
    @Basic
    @Column(name = "priority_pass")
    private byte priorityPass;
    @Basic
    @Column(name = "use_date")
    private Date useDate;
    @OneToOne
    @JoinColumn(name = "dysney_park_id", referencedColumnName = "id", nullable = false)
    private DysneyPark dysneyPark;
    @OneToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
    private Customer customer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public byte getPriorityPass() {
        return priorityPass;
    }

    public void setPriorityPass(byte priorityPass) {
        this.priorityPass = priorityPass;
    }

    public Date getUseDate() {
        return useDate;
    }

    public void setUseDate(Date useDate) {
        this.useDate = useDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tickets that = (Tickets) o;
        return id == that.id && priorityPass == that.priorityPass && Objects.equals(price, that.price) && Objects.equals(useDate, that.useDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, priorityPass, useDate);
    }

    public DysneyPark getDysneyPark() {
        return dysneyPark;
    }

    public void setDysneyPark(DysneyPark dysneyPark) {
        this.dysneyPark = dysneyPark;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
