package de.req4test.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TestCase {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    private String title;
    private String status;

    public TestCase() {
        this(null);
    }
    public TestCase(String t) {
        this(t,"NOTRUN");
    }

    public TestCase(String t, String s) {
        title = t;
        status = s;
    }

    public int getId() {
        return id;
    }

    public void setId(int value) {
        id = value;
    }

    public String getCode() {
        return "TST-" + id;
    }

    public String getStatus() {
        return status;
    }

    public String getTitle() {
        return title;
    }

    public void getTitle(String value) {
        title = value;
    }

    public void setStatus(String value) {
        status = value;
    }
}