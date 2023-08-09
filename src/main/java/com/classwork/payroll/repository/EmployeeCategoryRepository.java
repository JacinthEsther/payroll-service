package com.classwork.payroll.repository;

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
public class EmployeeCategoryRepository implements RepositoryInterface<EmployeeCategory>{
    @PersistenceContext
    private EntityManager em;

    @Override
    public EmployeeCategory create(EmployeeCategory obj) {
        em.persist(obj);
        return obj;
    }

    @Override
    public EmployeeCategory update(EmployeeCategory obj) {
        return em.merge(obj);
    }

    @Override
    public Optional<EmployeeCategory> findById(int id) {
        TypedQuery<EmployeeCategory> query =
                em.createQuery("SELECT t FROM EmployeeCategory t WHERE t.id = :id", EmployeeCategory.class);
        query.setParameter("id", id);

        List<EmployeeCategory> employeeCategories = query.getResultList();
        if (!employeeCategories.isEmpty()) {
            EmployeeCategory employeeCategory = employeeCategories.get(0);
            return Optional.of(employeeCategory);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public List<EmployeeCategory> findAll() {
        TypedQuery<EmployeeCategory> query = em.createQuery("SELECT t from EmployeeCategory t", EmployeeCategory.class);
        return query.getResultList();
    }

    public Optional<EmployeeCategory> findByName(String name) {
        TypedQuery<EmployeeCategory> query =
                em.createQuery("SELECT t FROM EmployeeCategory t WHERE t.name = :name", EmployeeCategory.class);
        query.setParameter("name", name);

        List<EmployeeCategory> employeeCategories = query.getResultList();
        if (!employeeCategories.isEmpty()) {
            EmployeeCategory employeeCategory = employeeCategories.get(0);
            return Optional.of(employeeCategory);
        } else {
            return Optional.empty();
        }
    }
}
