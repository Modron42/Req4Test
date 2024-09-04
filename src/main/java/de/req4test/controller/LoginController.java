package de.req4test.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import de.req4test.Repository;
import de.req4test.entity.Employee;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@SessionScoped
public class LoginController implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private String username;
    private String password;
    private String fullName;
    private Repository repository;

    @Inject
    public LoginController(Repository repo) {
        repository = repo;
        id = 0;
        fullName = "n/a";
    }

    public boolean isLoggedIn() {
        return id != 0;
    }

    public int getId() {
        return id;
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

    public String getFullName() {
        return fullName;
    }

    public void login() {
        List<Employee> users = repository.getEmployees();
        Optional<Employee> me = users.stream().filter(x -> username.equals(x.getUsername())).findFirst();
        if(me.isPresent()) {
            Employee credentials = me.get();
            if(credentials!=null && 
            credentials.getUsername()!=null && credentials.getUsername().equals(username) && 
            credentials.getPassword()!=null && credentials.getPassword().equals(password) ) {
                id = credentials.getId();
                fullName = credentials.getFullName();
                password = null;
            }
        }
    }

    public void logout() {
        id = 0;
        username = null;
        password = null;
        fullName = "n/a";
    }
}