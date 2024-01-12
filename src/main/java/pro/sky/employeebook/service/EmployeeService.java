package pro.sky.employeebook.service;

import pro.sky.employeebook.model.Employee;

public interface EmployeeService {
    Employee add(Employee employee);
    Employee remove(Employee employee);
    Employee find(Employee employee);
}
