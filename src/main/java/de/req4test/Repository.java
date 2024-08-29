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
        List<Employee> employees = entityManager.createQuery("SELECT e FROM Employee e ORDER by id", Employee.class)
                .getResultList();
        entityManager.close();
        return employees;
    }

    public Employee getEmployee(int id) {
        EntityManager entityManager = database.createEntityManager();
        Employee emp = entityManager.find(Employee.class, id);
        entityManager.close();
        return emp;
    }

    public void addEmployee(String firstName, String lastName, String username, String password) {
        EntityManager entityManager = database.createEntityManager();
        Employee employee = new Employee(firstName, lastName, username, password);
        entityManager.getTransaction().begin();
        entityManager.persist(employee);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void updateEmployee(Employee employee) {
        EntityManager entityManager = database.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(employee);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public List<Requirement> getRequirements() {
        EntityManager entityManager = database.createEntityManager();
        List<Requirement> reqs = entityManager.createQuery("SELECT e FROM Requirement e ORDER by id", Requirement.class)
                .getResultList();
        entityManager.close();
        return reqs;
    }

    public Requirement getRequirement(int id) {
        EntityManager entityManager = database.createEntityManager();
        Requirement req = entityManager.find(Requirement.class, id);
        entityManager.close();
        return req;
    }

    public void addRequirement(String title) {
        EntityManager entityManager = database.createEntityManager();
        Requirement requirement = new Requirement(title, "");
        entityManager.getTransaction().begin();
        entityManager.persist(requirement);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void updateRequirement(Requirement requirement) {
        EntityManager entityManager = database.createEntityManager();
        entityManager.getTransaction().begin();
        for (TestCase tc : requirement.getTestCases()) {
            entityManager.merge(tc);
        }
        entityManager.merge(requirement);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public List<TestCase> getTestCases() {
        EntityManager entityManager = database.createEntityManager();
        List<TestCase> tests = entityManager.createQuery("SELECT e FROM TestCase e ORDER by id", TestCase.class)
                .getResultList();
        entityManager.close();
        return tests;
    }

    public TestCase getTestCase(int id) {
        EntityManager entityManager = database.createEntityManager();
        TestCase tst = entityManager.find(TestCase.class, id);
        entityManager.close();
        return tst;
    }

    public void addTestCase(String title) {
        EntityManager entityManager = database.createEntityManager();
        TestCase test = new TestCase(title);
        entityManager.getTransaction().begin();
        entityManager.persist(test);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void updateTestCase(TestCase test) {
        EntityManager entityManager = database.createEntityManager();
        entityManager.getTransaction().begin();
        for (Requirement rq : test.getRequirements()) {
            entityManager.merge(rq);
        }
        entityManager.merge(test);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public List<TestRun> getTestRuns() {
        EntityManager entityManager = database.createEntityManager();
        List<TestRun> runs = entityManager.createQuery("SELECT e FROM TestRun e ORDER by id", TestRun.class)
                .getResultList();
        entityManager.close();
        return runs;
    }

    public TestRun getTestRun(int id) {
        EntityManager entityManager = database.createEntityManager();
        TestRun run = entityManager.find(TestRun.class, id);
        entityManager.close();
        return run;
    }

    public void addTestRun() {
        EntityManager entityManager = database.createEntityManager();
        TestRun run = new TestRun();
        entityManager.getTransaction().begin();
        entityManager.persist(run);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void updateTestRun(TestRun run) {
        EntityManager entityManager = database.createEntityManager();
        entityManager.getTransaction().begin();
        if (run.getAssignee() != null) {
            entityManager.merge(run.getAssignee());
        }
        for (TestCase tc : run.getTestCases()) {
            entityManager.merge(tc);
        }
        entityManager.merge(run);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}