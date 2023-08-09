package com.classwork.payroll.repository;

import com.classwork.payroll.model.SalaryRate;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class SalaryRateRepository implements RepositoryInterface<SalaryRate>{

    @PersistenceContext
    private EntityManager em;

    @Override
    public SalaryRate create(SalaryRate obj) {
        em.persist(obj);
        return obj;
    }

    @Override
    public SalaryRate update(SalaryRate obj) {
        return em.merge(obj);
    }

    @Override
    public Optional<SalaryRate> findById(int id) {
        TypedQuery<SalaryRate> query =
                em.createQuery("SELECT t FROM SalaryRatet WHERE t.id = :id", SalaryRate.class);
        query.setParameter("id", id);

        List<SalaryRate> salaryRates = query.getResultList();
        if (!salaryRates.isEmpty()) {
            SalaryRate salaryRate = salaryRates.get(0);
            return Optional.of(salaryRate);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public List<SalaryRate> findAll() {
        TypedQuery<SalaryRate> query = em.createQuery("SELECT t from SalaryRate t", SalaryRate.class);
        return query.getResultList();
    }

}
