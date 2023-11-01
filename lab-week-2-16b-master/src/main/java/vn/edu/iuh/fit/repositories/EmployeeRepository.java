package vn.edu.iuh.fit.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import vn.edu.iuh.fit.enums.EmployeeStatus;
import vn.edu.iuh.fit.models.Employee;

import java.util.List;
import java.util.Optional;

public class EmployeeRepository {
    private EntityManager em;

    public EmployeeRepository() {
        em = Persistence
                .createEntityManagerFactory("lab_week_2")
                .createEntityManager();
    }

    public void insertEmp(Employee employee) {
        em.persist(employee);
    }

    public void setStatus(Employee employee, EmployeeStatus status) {
        employee.setStatus(status);
    }

    public void update(Employee employee) {
        em.merge(em);
    }

    public Optional<Employee> findbyId(long id) {
        TypedQuery<Employee> query = em.createQuery("select e from Employee e where e.id=:id", Employee.class);
        query.setParameter("id", id);
        Employee emp = query.getSingleResult();
        return emp == null ? Optional.empty() : Optional.of(emp);
    }

    public List<Employee> getAllEmp() {
        return em.createNamedQuery("Employee.findAll", Employee.class).getResultList();
    }
}
