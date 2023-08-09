package com.classwork.payroll.repository;

import com.classwork.payroll.model.Employee;
import com.classwork.payroll.model.EmployeeCategory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
@Transactional
public class EmployeeRepository implements RepositoryInterface<Employee> {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Employee create(Employee obj) {
        em.persist(obj);
        return obj;
    }

    @Override
    public Employee update(Employee obj) {
        return em.merge(obj);
    }

    @Override
    public Optional<Employee> findById(int id) {
        TypedQuery<Employee> query =
                em.createQuery("SELECT t FROM Employee t WHERE t.id = :id", Employee.class);
        query.setParameter("id", id);

        List<Employee> employees = query.getResultList();
        if (!employees.isEmpty()) {
            Employee employee = employees.get(0);
            return Optional.of(employee);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public List<Employee> findAll() {
        TypedQuery<Employee> query = em.createQuery("SELECT t from Employee t", Employee.class);
        return query.getResultList();
    }

    public Optional<Employee> findByPhoneNumber(String phone) {
        TypedQuery<Employee> query =
                em.createQuery("SELECT t FROM Employee t WHERE t.phone = :phone", Employee.class);
        query.setParameter("phone", phone);

        List<Employee> employees = query.getResultList();
        if (!employees.isEmpty()) {
            Employee employee = employees.get(0);
            return Optional.of(employee);
        } else {
            return Optional.empty();
        }
    }
    public List<Employee> findAllByEmployeeCategoryAndEmployeeLevel(EmployeeCategory employeeCategory, int employeeLevel) {
        TypedQuery<Employee> query =
                em.createQuery("SELECT e FROM Employee e " +
                        "WHERE e.employeeCategory = :employeeCategory " +
                        "AND e.employeeLevel = :employeeLevel", Employee.class);
        query.setParameter("employeeCategory", employeeCategory);
        query.setParameter("employeeLevel", employeeLevel);

        return query.getResultList();
    }

    public List<Employee> findAllByDepartmentId(int departmentId) {
        TypedQuery<Employee> query = em.createQuery(
                "SELECT e FROM Employee e WHERE e.employeeDepartment.id = :departmentId", Employee.class);
        query.setParameter("departmentId", departmentId);
        return query.getResultList();
    }

    public List<Employee> findAllManagedByManager(int managerId) {
        TypedQuery<Employee> query = em.createQuery(
                "SELECT e FROM Employee e WHERE e.managerDepartment.managerId = :managerId", Employee.class);
        query.setParameter("managerId", managerId);
        return query.getResultList();
    }

}