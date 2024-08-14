package de.req4test.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Requirement {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    private String title;
    private String text;

    public Requirement() {

    }

    public Requirement(String t, String content) {
        title = t;
        text = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int value) {
        id = value;
    }

    public String getCode() {
        return "REQ-" + id;
    }

    public String getTitle() {
        return title;
    }

    public void getTitle(String value) {
        title = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String value) {
        text = value;
    }
}