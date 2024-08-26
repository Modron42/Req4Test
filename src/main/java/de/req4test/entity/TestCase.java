package de.req4test.entity;

import java.util.ArrayList;
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
public class TestCase {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    private String title;
    private String status;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Requirement> requirements = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<TestRun> runs = new HashSet<>();

    private List<String> steps = new ArrayList<>();

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

    public Set<Requirement> getRequirements() {
        return requirements;
    }

    public void addRequirement(Requirement value) {
        boolean already = requirements.stream().anyMatch(x -> x.getId() == value.getId());
        if(!already) {
            requirements.add(value);
            value.addTestCase(this);
        }
    }

    public void removeRequirement(int value) {
        List<Requirement> matching = requirements.stream().filter( x -> x.getId() == id).collect(Collectors.toList());
        requirements.removeIf( x -> x.getId() == value );
        matching.forEach( x -> x.removeTestCase(id) );
    }

    public Set<TestRun> getTestRuns() {
        return runs;
    }

    public void addTestRun(TestRun value) {
        runs.add(value);
    }

    public void removeTestRun(int value) {
        List<TestRun> matching = runs.stream().filter( x -> x.getId() == id).collect(Collectors.toList());
        runs.removeIf( x -> x.getId() == value );
        matching.forEach( x -> x.removeTestCase(id) );
    }

    public List<String> getSteps() {
        return steps;
    }

    public void setSteps(List<String> value) {
        steps = value;
    }

    public void addStep(String step) {
        steps.add(step);
    }
    public boolean isSucceeded() {
        return status!=null && status.equals("SUCCEEDED");
    }
}