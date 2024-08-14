package de.req4test;

import java.util.List;

import de.req4test.entity.Employee;
import de.req4test.entity.Requirement;
import de.req4test.entity.TestCase;
import de.req4test.entity.TestRun;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;

@Named
@RequestScoped
public class Repository {
    private Database database;

    protected Repository() {

    }

    @Inject
    public Repository(Database db) {
        database = db;
    }

    public List<Employee> getEmployees() {
        EntityManager entityManager = database.createEntityManager();
        List<Employee> employees = entityManager.createQuery("SELECT e FROM Employee e", Employee.class)
                .getResultList();
        entityManager.close();
        return employees;
    }

    public List<Requirement> getRequirements() {
        EntityManager entityManager = database.createEntityManager();
        List<Requirement> reqs = entityManager.createQuery("SELECT e FROM Requirement e", Requirement.class)
                .getResultList();
        entityManager.close();
        return reqs;
    }

    public List<TestCase> getTestCases() {
        EntityManager entityManager = database.createEntityManager();
        List<TestCase> tests = entityManager.createQuery("SELECT e FROM TestCase e", TestCase.class)
                .getResultList();
        entityManager.close();
        return tests;
    }

    public List<TestRun> getTestRuns() {
        EntityManager entityManager = database.createEntityManager();
        List<TestRun> runs = entityManager.createQuery("SELECT e FROM TestRun e", TestRun.class)
                .getResultList();
        entityManager.close();
        return runs;
    }
}