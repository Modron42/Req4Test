package de.req4test.controller;

import java.io.Serializable;

import de.req4test.Repository;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ViewScoped
public class TestCaseController implements Serializable {

    private static final long serialVersionUID = 1L;
    private String title;
    private String status;
    private Repository repository;

    @Inject
    public TestCaseController(Repository repo) {
        repository = repo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String value) {
        status = value;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String value) {
        title = value;
    }

    public void create() {
        repository.addTestCase(title);
        title = null;
    }
}