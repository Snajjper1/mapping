package com.perscholas.services;

import com.perscholas.MainRunner;
import com.perscholas.dao.IEmployee;
import com.perscholas.models.Employee;
import lombok.extern.log4j.Log4j;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.TransactionRequiredException;

@Log4j
public class EmployeeServices implements IEmployee {
    @Override
    public void createEmployee(Employee employee) {
        EntityManager em = MainRunner.emf.createEntityManager();

        try {
            em.getTransaction().begin();

            em.persist(employee);

            em.getTransaction().commit();
            log.info(employee);
        } catch (IllegalStateException | EntityExistsException | TransactionRequiredException | IllegalArgumentException e) {
            em.getTransaction().rollback();
            log.error("error persisting employee: " + employee);
            e.printStackTrace();
        } finally {
            em.close();
            log.info("Entity Manager is Closed!");
        }

    }
}
