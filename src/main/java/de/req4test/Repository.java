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

    public void addEmployee(String firstName, String lastName, String username, String password) {
        EntityManager entityManager = database.createEntityManager();
        Employee employee = new Employee(firstName, lastName, username, password);
        entityManager.getTransaction().begin();
        entityManager.persist(employee);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public List<Requirement> getRequirements() {
        EntityManager entityManager = database.createEntityManager();
        List<Requirement> reqs = entityManager.createQuery("SELECT e FROM Requirement e", Requirement.class)
                .getResultList();
        entityManager.close();
        return reqs;
    }

    public void addRequirement(String title) {
        EntityManager entityManager = database.createEntityManager();
        Requirement requirement = new Requirement(title, "");
        entityManager.getTransaction().begin();
        entityManager.persist(requirement);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public List<TestCase> getTestCases() {
        EntityManager entityManager = database.createEntityManager();
        List<TestCase> tests = entityManager.createQuery("SELECT e FROM TestCase e", TestCase.class)
                .getResultList();
        entityManager.close();
        return tests;
    }

    public void addTestCase(String title) {
        EntityManager entityManager = database.createEntityManager();
        TestCase test = new TestCase(title);
        entityManager.getTransaction().begin();
        entityManager.persist(test);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public List<TestRun> getTestRuns() {
        EntityManager entityManager = database.createEntityManager();
        List<TestRun> runs = entityManager.createQuery("SELECT e FROM TestRun e", TestRun.class)
                .getResultList();
        entityManager.close();
        return runs;
    }

    public void addTestRun() {
        EntityManager entityManager = database.createEntityManager();
        TestRun run = new TestRun();
        entityManager.getTransaction().begin();
        entityManager.persist(run);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}