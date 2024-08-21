package de.req4test.controller;

import java.io.Serializable;

import de.req4test.Repository;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ViewScoped
public class RequirementController implements Serializable {

    private static final long serialVersionUID = 1L;

    private String title;
    private String text;
    private Repository repository;

    @Inject
    public RequirementController(Repository repo) {
        repository = repo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String value) {
        title = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String value) {
        text = value;
    }

    public void create() {
        repository.addRequirement(title);
        title = null;
    }
}