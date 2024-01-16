package pro.sky.employeebook.service;

import org.springframework.stereotype.Service;
import pro.sky.employeebook.exception.EmployeeAlreadyAddedException;
import pro.sky.employeebook.exception.EmployeeNotFoundException;
import pro.sky.employeebook.exception.EmployeeStorageIsFullException;
import pro.sky.employeebook.model.Employee;

import java.util.HashMap;


@Service
public class EmployeeServiceImpl implements EmployeeService {
    private HashMap<String, Employee> employeeMap;

    private String employeeKey(Employee employee) {
        return employee.getFirstName() + employee.getLastName();
    }


    public EmployeeServiceImpl(HashMap<String, Employee> employeeMap) {
        this.employeeMap = employeeMap;
    }

    private final int maxEmployees = 2;

    @Override
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

    @Override
    public Employee remove(Employee employee) throws EmployeeNotFoundException {
        if (employeeMap.containsKey(employeeKey(employee))) {
            employeeMap.remove(employeeKey(employee));
            return employee;
        } else {
            throw new EmployeeNotFoundException("EmployeeNotFound");
        }
    }

    @Override
    public Employee find(Employee employee) throws EmployeeNotFoundException {
        if (employeeMap.containsKey(employeeKey(employee))) {
            return employee;
        } else {
            throw new EmployeeNotFoundException("EmployeeNotFound");
        }
    }
}
