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
import jakarta.persistence.ManyToOne;

@Entity
public class TestRun {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    Employee assignee;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<TestCase> tests = new HashSet<>();

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

    public String getCoverage() {
        long succeeded = tests.stream().filter(x -> x.isSucceeded()).count();
        return succeeded + " / " + tests.size();
    }

    public Employee getAssignee() {
        return assignee;
    }

    public void setAssignee(Employee value) {
        assignee = value;
    }

    public Set<TestCase> getTestCases() {
        return tests;
    }

    public void addTestCase(TestCase value) {
        boolean already = tests.stream().anyMatch(x -> x.getId() == value.getId());
        if(!already) {
            tests.add(value);
            value.addTestRun(this);
        }
    }

    public void removeTestCase(int value) {
        List<TestCase> matching = tests.stream().filter( x -> x.getId() == value).collect(Collectors.toList());
        tests.removeIf( x -> x.getId() == value );
        matching.forEach( x -> x.removeTestRun(id) );
    }
}