package de.req4test.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TestRun {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    public TestRun() {

    }

    public int getId() {
        return id;
    }

    public void setId(int value) {
        id = value;
    }

    public String getCode() {
        return "RUN-" + id;
    }
}