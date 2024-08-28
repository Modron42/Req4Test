package de.req4test.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import de.req4test.Repository;
import de.req4test.entity.Employee;
import de.req4test.entity.TestCase;
import de.req4test.entity.TestRun;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ViewScoped
public class TestRunController implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String code;
    private List<TestCase> testCases;
    private Employee assignee;
    private int selected;
    private int selectedAssignee;
    private Repository repository;

    @Inject
    public TestRunController(Repository repo) {
        repository = repo;
    }

    public int getId() {
        return id;
    }

    public void setId(int value) {
        id = value;
        TestRun tst = repository.getTestRun(value);
        code = tst.getCode();
        testCases = tst.getTestCases().stream().sorted((x, y) -> Integer.compare(x.getId(), y.getId())).collect(Collectors.toList());
        assignee = tst.getAssignee();
    }

    public String getCode() {
        return code;
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

    public int getSelectedAssignee() {
        return selectedAssignee;
    }

    public void setSelectedAssignee(int value) {
        selectedAssignee = value;
    }

    public String getAssignee() {
        return assignee != null ? assignee.getFullName() : "n/a";
    }

    public List<Employee> getAvailableAssignees() {
        return repository.getEmployees();
    }

    public void addTestCase() {
        TestRun run = repository.getTestRun(id);
        TestCase test = repository.getTestCase(selected);
        run.addTestCase(test);
        repository.updateTestRun(run);
        setId(id);
    }

    public void removeTestCase(int value) {
        TestRun run = repository.getTestRun(id);
        run.removeTestCase(value);
        repository.updateTestRun(run);
        setId(id);
    }

    public List<TestRun> assignedRuns(int value) {
        if(value != 0) {
            List<TestRun> all = repository.getTestRuns();
            List<TestRun> result = all.stream().filter(x -> x.getAssignee().getId() == value).collect(Collectors.toList());
            return result;
        } else {
            return new ArrayList<>();
        }
    }

    public void update() {
        Employee emp = repository.getEmployee(selectedAssignee);
        TestRun run = repository.getTestRun(id);
        run.setAssignee(emp);
        repository.updateTestRun(run);
        setId(id);
    }

    public void create() {
        repository.addTestRun();
    }
}