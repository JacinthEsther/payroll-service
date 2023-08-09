package com.classwork.payroll.repository;

import com.classwork.payroll.model.Department;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class DepartmentRepository implements RepositoryInterface<Department>{
    @PersistenceContext
    private EntityManager em;
    @Override
    public Department create(Department obj) {
        em.persist(obj);
        return obj;
    }

    @Override
    public Department update(Department obj) {
        return em.merge(obj);
    }

    @Override
    public Optional<Department> findById(int id) {
        TypedQuery<Department> query =
                em.createQuery("SELECT t FROM Department t WHERE t.id = :id", Department.class);
        query.setParameter("id", id);

        List<Department> departments = query.getResultList();
        if (!departments.isEmpty()) {
            Department department = departments.get(0);
            return Optional.of(department);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public List<Department> findAll() {
        TypedQuery<Department> query = em.createQuery("SELECT t from Department t", Department.class);
        return query.getResultList();
    }

    public Optional<Department> findByName(String name) {
        TypedQuery<Department> query =
                em.createQuery("SELECT t FROM Department t WHERE t.name = :name", Department.class);
        query.setParameter("name", name);

        List<Department> departments = query.getResultList();
        if (!departments.isEmpty()) {
            Department department = departments.get(0);
            return Optional.of(department);
        } else {
            return Optional.empty();
        }
    }
}
