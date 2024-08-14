package de.req4test;

import de.req4test.entity.Employee;
import de.req4test.entity.Requirement;
import de.req4test.entity.TestCase;
import de.req4test.entity.TestRun;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

@Named
@ApplicationScoped
public class Database {

    private EntityManagerFactory entityManagerFactory;

    public EntityManager createEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    public Database() {
        entityManagerFactory = Persistence.createEntityManagerFactory("req4test");
        Employee usr1 = new Employee("Alice", "Requirements Engineer", "alice", "pass");
        Employee usr2 = new Employee("Bob", "Test Manager", "bob", "pass");
        Employee usr3 = new Employee("Charlie", "Test Engineer", "charlie", "pass");

        Requirement req1 = new Requirement("As a requirment engineer I want to create requirements to test.",
                "We are creating a requirements management tool.");
        Requirement req2 = new Requirement("As a test manager I want to create test cases.",
                "We want to perform test management in the same tool as requirements management to avoid export.");
        Requirement req3 = new Requirement("As a test engineer I want to create test cases for a requirement.",
                "There shall be a bidirectional traceability between requirements and test cases");
        Requirement req4 = new Requirement("As a test manager I want to assign tests and test engineer to a test run.",
                "");
        Requirement req5 = new Requirement("As a requirment engineer I want to create requirements to test.", "");
        Requirement req6 = new Requirement("As a test engineer I want add results to the test cases assigned to me.",
                "");
        Requirement req7 = new Requirement("As a test manager I want to get an overview over the test runs.", "");
        Requirement req8 = new Requirement("As a test engineer I want to add individual steps to test cases.", "");

        TestCase tst1 = new TestCase("Unit tests", "NOTRUN");
        TestCase tst2 = new TestCase("Performance tests", "NOTRUN");
        TestCase tst3 = new TestCase("Acceptance tests", "NOTRUN");

        TestRun run1 = new TestRun();

        EntityManager entityManager = createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(usr1);
        entityManager.persist(usr2);
        entityManager.persist(usr3);

        entityManager.persist(req1);
        entityManager.persist(req2);
        entityManager.persist(req3);
        entityManager.persist(req4);
        entityManager.persist(req5);
        entityManager.persist(req6);
        entityManager.persist(req7);
        entityManager.persist(req8);

        entityManager.persist(tst1);
        entityManager.persist(tst2);
        entityManager.persist(tst3);

        entityManager.persist(run1);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

}