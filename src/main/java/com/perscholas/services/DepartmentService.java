package com.perscholas.services;

import com.perscholas.MainRunner;
import com.perscholas.dao.IDepartment;
import com.perscholas.models.Department;
import com.perscholas.models.Employee;
import lombok.extern.log4j.Log4j;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TransactionRequiredException;
import java.util.List;

@Log4j
public class DepartmentService implements IDepartment {
    @Override
    public void createDepartment(Department d) {
        // session
        EntityManager em = MainRunner.emf.createEntityManager();
        try {
            // transaction

            em.getTransaction().begin();
            // save
            em.persist(d);
            //commit
            em.getTransaction().commit();

            log.info(d);
        } catch(IllegalStateException | EntityExistsException | IllegalArgumentException | TransactionRequiredException e ){
            em.getTransaction().rollback();
            log.error("Error persisting department: " + d);
            e.printStackTrace();

        } finally {
            //close em

            em.close();
            log.info("EntityManager is closed!");
        }
    }

    @Override
    public void addEmployeeToDepartment(Employee e, int deptId) {

        // session
        EntityManager em = MainRunner.emf.createEntityManager();
        Department department = null;
        try {
            // transaction
            em.getTransaction().begin();
            // logic
             department = em.find(Department.class, deptId);
            List<Employee> empList = department.getEmployeeList();
            empList.add(em.find(Employee.class, e.getEId()));


            // commit or rollback
            em.getTransaction().commit();
            log.info("Department: " + department + "Employee:  " + e);
            // handle exceptions
        } catch(IllegalStateException | EntityExistsException | IllegalArgumentException | TransactionRequiredException ex ){
            ex.printStackTrace();
            log.error("Error persisting department add employee: " + department);
        }finally {
            // end session
            em.close();
            log.info("Session ended!");
        }


    }
}
