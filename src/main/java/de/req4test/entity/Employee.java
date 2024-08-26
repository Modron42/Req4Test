package de.req4test.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName;
    private String lastName;
    private String username;
    private String password;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<TestRun> runs = new HashSet<>();

    public Employee() {

    }

    public Employee(String fn, String ln, String un, String pass) {
        firstName = fn;
        lastName = ln;
        username = un;
        password = pass;
    }

    public int getId() {
        return id;
    }

    public void setId(int value) {
        id = value;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String value) {
        firstName = value;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String value) {
        lastName = value;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String value) {
        username = value;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String value) {
        password = value;
    }

    public Set<TestRun> getRuns() {
        return runs;
    }

    public void addRun(TestRun value) {
        runs.add(value);
    }

    public void removeRun(TestRun value) {
        runs.remove(value);
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String toString() {
        return getFullName();
    }
}