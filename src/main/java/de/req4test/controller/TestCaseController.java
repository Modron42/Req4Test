package de.req4test.controller;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
    private int selected;
    private int selectedRun;
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

    public Set<Requirement> getAvailableRequirements() {
        List<Requirement> reqs = repository.getRequirements();
        if (requirements != null) {
            Set<Requirement> result = reqs.stream()
                    .filter(x -> !requirements.stream().anyMatch(y -> x.getId() == y.getId()))
                    .collect(Collectors.toSet());
            return result;
        } else {
            return new HashSet<>(reqs);
        }
    }

    public List<TestRun> getTestRuns() {
        return runs;
    }

    public Set<TestRun> getAvailableTestRuns() {
        List<TestRun> rs = repository.getTestRuns();
        if (requirements != null) {
            Set<TestRun> result = rs.stream().filter(x -> !requirements.stream().anyMatch(y -> x.getId() == y.getId()))
                    .collect(Collectors.toSet());
            return result;
        } else {
            return new HashSet<>(rs);
        }
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

    public int getSelected() {
        return selected;
    }

    public void setSelected(int value) {
        selected = value;
    }

    public int getSelectedRun() {
        return selectedRun;
    }

    public void setSelectedRun(int value) {
        selectedRun = value;
    }

    public void addRequirement() {
        TestCase test = repository.getTestCase(id);
        Requirement req = repository.getRequirement(selected);
        test.addRequirement(req);
        repository.updateTestCase(test);
        setId(id);
    }

    public void addTestRun() {
        TestCase test = repository.getTestCase(id);
        TestRun testRun = repository.getTestRun(selectedRun);
        test.addTestRun(testRun);
        repository.updateTestCase(test);
        setId(id);
    }

    public void removeRequirement(int value) {
        TestCase test = repository.getTestCase(id);
        test.removeRequirement(value);
        repository.updateTestCase(test);
        setId(id);
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