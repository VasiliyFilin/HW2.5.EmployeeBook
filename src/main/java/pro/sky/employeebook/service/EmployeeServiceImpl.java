package pro.sky.employeebook.service;

import org.springframework.stereotype.Service;
import pro.sky.employeebook.exception.EmployeeAlreadyAddedException;
import pro.sky.employeebook.exception.EmployeeNotFoundException;
import pro.sky.employeebook.exception.EmployeeStorageIsFullException;
import pro.sky.employeebook.model.Employee;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final List<Employee> employeeList;

    public EmployeeServiceImpl(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    private final int maxEmployees = 2;

    @Override
    public Employee add(Employee employee) throws EmployeeAlreadyAddedException, EmployeeStorageIsFullException {
        if (employeeList.size() < maxEmployees) {
            if (employeeList.contains(employee)) {
                throw new EmployeeAlreadyAddedException("EmployeeAlreadyAdded");
            }
            employeeList.add(employee);
            return employee;
        } else {
            throw new EmployeeStorageIsFullException("EmployeeStorageIsFull");
        }
    }

    @Override
    public Employee remove(Employee employee) throws EmployeeNotFoundException {
        if (employeeList.contains(employee)) {
            employeeList.remove(employee);
            return employee;
        } else {
            throw new EmployeeNotFoundException("EmployeeNotFound");
        }
    }

    @Override
    public Employee find(Employee employee) throws EmployeeNotFoundException {
        if (employeeList.contains(employee)) {
            return employee;
        } else {
            throw new EmployeeNotFoundException("EmployeeNotFound");
        }
    }
    public Collection<Employee> listEmployee() {
        return Collections.unmodifiableList(employeeList);
    }
}
