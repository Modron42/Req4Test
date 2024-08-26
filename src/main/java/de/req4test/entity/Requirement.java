package de.req4test.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Requirement {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    private String title;
    private String text;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<TestCase> tests = new HashSet<>();

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

    public Set<TestCase> getTestCases() {
        return tests;
    }

    public void addTestCase(TestCase value) {
        boolean already = tests.stream().anyMatch(x -> x.getId() == value.getId());
        if(!already) {
            tests.add(value);
            value.addRequirement(this);
        }
    }

    public void removeTestCase(int value) {
        List<TestCase> matching = tests.stream().filter( x -> x.getId() == value).collect(Collectors.toList());
        tests.removeIf( x -> x.getId() == value );
        matching.forEach( x -> x.removeRequirement(id) );
    }
}