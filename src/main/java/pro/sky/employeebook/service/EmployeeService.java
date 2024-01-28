package pro.sky.employeebook.service;

import org.springframework.stereotype.Service;
import pro.sky.employeebook.exception.EmployeeAlreadyAddedException;
import pro.sky.employeebook.exception.EmployeeNotFoundException;
import pro.sky.employeebook.exception.EmployeeStorageIsFullException;
import pro.sky.employeebook.model.Employee;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


@Service
public class EmployeeService {
    private static final int maxEmployees = 5;
    private final Map<String, Employee> employeeMap = new HashMap<>(maxEmployees);

    private String employeeKey(Employee employee) {
        return employee.getFirstName() + employee.getLastName() + employee.getDepartment();
    }

    public Employee add(Employee employee) throws EmployeeAlreadyAddedException, EmployeeStorageIsFullException {
        if (employeeMap.size() < maxEmployees) {
            if (employeeMap.containsKey(employeeKey(employee))) {
                throw new EmployeeAlreadyAddedException("EmployeeAlreadyAdded");
            }
            employeeMap.put(employeeKey(employee), employee);
            return employee;
        } else {
            throw new EmployeeStorageIsFullException("EmployeeStorageIsFull");
        }
    }


    public Employee remove(Employee employee) throws EmployeeNotFoundException {
        if (employeeMap.containsKey(employeeKey(employee))) {
            employeeMap.remove(employeeKey(employee));
            return employee;
        } else {
            throw new EmployeeNotFoundException("EmployeeNotFound");
        }
    }


    public Employee find(Employee employee) throws EmployeeNotFoundException {
        if (employeeMap.containsKey(employeeKey(employee))) {
            return employee;
        } else {
            throw new EmployeeNotFoundException("EmployeeNotFound");
        }
    }

    public Collection<Employee> getAll() {
        return Collections.unmodifiableCollection(employeeMap.values());
    }
}
