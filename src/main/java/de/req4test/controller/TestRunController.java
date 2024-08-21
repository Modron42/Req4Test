package de.req4test.controller;

import java.io.Serializable;

import de.req4test.Repository;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ViewScoped
public class TestRunController implements Serializable {

    private static final long serialVersionUID = 1L;

    private Repository repository;

    @Inject
    public TestRunController(Repository repo) {
        repository = repo;
    }

    public void create() {
        repository.addTestRun();
    }
}