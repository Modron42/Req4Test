package de.req4test.controller;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import de.req4test.Repository;
import de.req4test.entity.Requirement;
import de.req4test.entity.TestCase;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ViewScoped
public class RequirementController implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String title;
    private String code;
    private String text;
    private List<TestCase> testCases;
    private int selected;
    private Repository repository;

    @Inject
    public RequirementController(Repository repo) {
        repository = repo;
    }

    public int getId() {
        return id;
    }

    public void setId(int value) {
        id = value;
        Requirement req = repository.getRequirement(value);
        title = req.getTitle();
        code = req.getCode();
        text = req.getText();
        testCases = req.getTestCases().stream().sorted((x, y) -> Integer.compare(x.getId(), y.getId())).collect(Collectors.toList());
    }

    public String getCode() {
        return code;
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

    public List<TestCase> getTestCases() {
        return testCases;
    }

    public Set<TestCase> getAvailableTestCases() {
        List<TestCase> tests = repository.getTestCases();
        if (testCases != null) {
            Set<TestCase> result = tests.stream().filter(x -> !testCases.stream().anyMatch(y -> x.getId() == y.getId()))
                    .collect(Collectors.toSet());
            return result;
        } else {
            return new HashSet<>(tests);
        }
    }

    public int getSelected() {
        return selected;
    }

    public void setSelected(int value) {
        selected = value;
    }

    public void addTestCase() {
        Requirement req = repository.getRequirement(id);
        TestCase test = repository.getTestCase(selected);
        req.addTestCase(test);
        repository.updateRequirement(req);
        setId(id);
    }

    public void removeTestCase(int value) {
        Requirement req = repository.getRequirement(id);
        req.removeTestCase(value);
        repository.updateRequirement(req);
        setId(id);
    }
    
    public void update() {
        Requirement req = repository.getRequirement(id);
        req.getTitle(title);
        req.setText(text);
        repository.updateRequirement(req);
        setId(id);
    }

    public void create() {
        repository.addRequirement(title);
        title = null;
    }
}