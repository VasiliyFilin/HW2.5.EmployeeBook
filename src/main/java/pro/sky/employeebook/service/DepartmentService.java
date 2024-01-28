package pro.sky.employeebook.service;

import org.springframework.stereotype.Service;
import pro.sky.employeebook.model.Employee;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public Optional<Employee> findMaxSalary(int department) {
        return employeeService.getAll().stream()
                .filter(e -> e.getDepartment() == department)
                .max(Comparator.comparingInt(Employee::getSalary));
    }
    public Optional<Employee> findMinSalary(int department) {
        return employeeService.getAll().stream()
                .filter(e -> e.getDepartment() == department)
                .min(Comparator.comparingInt(Employee::getSalary));
    }

    public Collection<Employee> findAllByDepartment(int department) {
        return employeeService.getAll().stream()
                .filter(e -> e.getDepartment() == department)
                .collect(Collectors.toList());
    }

    public Map<Integer, List<Employee>> groupByDepartments() {
        return employeeService.getAll().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
