package vn.edu.iuh.fit.services;

import vn.edu.iuh.fit.enums.EmployeeStatus;
import vn.edu.iuh.fit.models.Employee;
import vn.edu.iuh.fit.models.Order;
import vn.edu.iuh.fit.repositories.EmployeeRepository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public class EmployeeServices {
    private EmployeeRepository repository;

    public EmployeeServices() {
        repository = new EmployeeRepository();
    }

    public void insertEmp(Employee employee) {
        repository.insertEmp(employee);
    }


    public Optional<Employee> findById(long id) {
        return repository.findbyId(id);
    }

    public boolean delete(long id) {
        Optional<Employee> op = findById(id);
        if (op.isPresent()) {
            Employee employee = op.get();
            employee.setStatus(EmployeeStatus.TERMINATED);
            return true;
        }
        return false;
    }

    public boolean activeEmp(long id) {
        Optional<Employee> op = findById(id);
        if (op.isPresent()) {
            Employee employee = op.get();
            employee.setStatus(EmployeeStatus.ACTIVE);
            return true;
        }
        return false;
    }

    public List<Employee> getAll() {
        return repository.getAllEmp();
    }

    public List<Order> getOrdersByPeriod(long empId, Date from, Date to) {
        //......
        return null;
    }
}
