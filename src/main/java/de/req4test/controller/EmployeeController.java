package de.req4test.controller;

import java.io.Serializable;

import de.req4test.Repository;
import de.req4test.entity.Employee;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ViewScoped
public class EmployeeController implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private Repository repository;

    @Inject
    public EmployeeController(Repository repo) {
        repository = repo;
    }

    public int getId() {
        return id;
    }

    public void setId(int value) {
        id = value;
        Employee emp = repository.getEmployee(value);
        firstName = emp.getFirstName();
        lastName = emp.getLastName();
        username = emp.getUsername();
        password = emp.getPassword();
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

    public void create() {
        repository.addEmployee(firstName, lastName, username, password);
        firstName = null;
        lastName = null;
        username = null;
        password = null;
    }

    public void update() {
        Employee emp = repository.getEmployee(id);
        emp.setFirstName(firstName);
        emp.setLastName(lastName);
        repository.updateEmployee(emp);
        setId(id);
    }

    public void updatePassword() {
        Employee emp = repository.getEmployee(id);
        emp.setPassword(password);
        repository.updateEmployee(emp);
        setId(id);
    }
}