package de.req4test.controller;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import de.req4test.Repository;
import de.req4test.entity.Requirement;
import de.req4test.entity.TestCase;
import de.req4test.entity.TestRun;
import jakarta.faces.model.ArrayDataModel;
import jakarta.faces.model.DataModel;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ViewScoped
public class TestCaseController implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String title;
    private String code;
    private String status;
    private List<Requirement> requirements;
    private List<TestRun> runs;
    private List<String> stepData;
    private DataModel<String> steps;
    private String newStep;
    private boolean stepsUpdated;
    private Repository repository;

    @Inject
    public TestCaseController(Repository repo) {
        repository = repo;
        stepsUpdated = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int value) {
        id = value;
        TestCase tst = repository.getTestCase(value);
        title = tst.getTitle();
        code = tst.getCode();
        status = tst.getStatus();
        if (!stepsUpdated) {
            stepData = tst.getSteps();
            steps = new ArrayDataModel<String>(stepData.toArray(new String[0]));
        }
        requirements = tst.getRequirements().stream().sorted((x, y) -> Integer.compare(x.getId(), y.getId())).collect(Collectors.toList());
        runs = tst.getTestRuns().stream().sorted((x, y) -> Integer.compare(x.getId(), y.getId())).collect(Collectors.toList());
    }

    public String getCode() {
        return code;
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

    public List<Requirement> getRequirements() {
        return requirements;
    }

    public List<TestRun> getTestRuns() {
        return runs;
    }

    public DataModel<String> getSteps() {
        return steps;
    }

    public void removeStep(int index) {
        stepsUpdated = true;
        stepData.remove(index);
        steps = new ArrayDataModel<String>(stepData.toArray(new String[0]));
    }

    public String getNewStep() {
        return newStep;
    }

    public void setNewStep(String value) {
        newStep = value;
    }

    public void addStep() {
        stepsUpdated = true;
        stepData.add(newStep);
        newStep = null;
        steps = new ArrayDataModel<String>(stepData.toArray(new String[0]));
    }

    public void update() {
        TestCase tst = repository.getTestCase(id);
        tst.getTitle(title);
        tst.setStatus(status);
        tst.setSteps(stepData);
        repository.updateTestCase(tst);
        stepsUpdated = false;
        setId(id);
    }

    public void create() {
        repository.addTestCase(title);
        stepsUpdated = false;
        title = null;
    }
}