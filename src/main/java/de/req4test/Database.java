package de.req4test;

import de.req4test.entity.Employee;
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

        EntityManager entityManager = createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(usr1);
        entityManager.persist(usr2);
        entityManager.persist(usr3);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

}