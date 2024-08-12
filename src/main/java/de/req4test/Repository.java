package de.req4test;

import java.util.List;

import de.req4test.entity.Employee;
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
}