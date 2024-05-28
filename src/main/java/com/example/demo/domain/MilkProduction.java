package com.example.demo.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class MilkProduction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "cow_id")
    @JsonBackReference("cowMilkProduction")
    private Cow cow;
    private Date date;
    private double amount;

    public MilkProduction() {
    }

    public MilkProduction(int id, Cow cow, Date date, double amount) {
        this.id = id;
        this.cow = cow;
        this.date = date;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public Cow getCow() {
        return cow;
    }

    public Date getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCow(Cow cow) {
        this.cow = cow;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "MilkProduction{" +
                "id=" + id +
                ", cow=" + cow +
                ", date=" + date +
                ", amount=" + amount +
                '}';
    }
}
