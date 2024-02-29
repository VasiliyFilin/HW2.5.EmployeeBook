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

    public Map<String, Employee> findAllByDepartment(int department) {
        return employeeService.getAll().stream()
                .filter(e -> department == e.getDepartment())
                .collect(Collectors.toMap(Employee::getFullName,e2 -> e2));
    }
    public Double salarySumByDepartment(int department) {
        return employeeService.getAll().stream()
                .filter(e -> e.getDepartment() == department).mapToDouble(Employee::getSalary).sum();
    }
    public int findMaxSalary(int department) {
        return employeeService.getAll().stream()
                .filter(e -> e.getDepartment() == department)
                .max(Comparator.comparingInt(Employee::getSalary)).orElseThrow().getSalary();
    }

    public int findMinSalary(int department) {
        return employeeService.getAll().stream()
                .filter(e -> e.getDepartment() == department)
                .min(Comparator.comparingInt(Employee::getSalary)).orElseThrow().getSalary();
    }

    public Map<Integer, List<Employee>> groupByDepartments() {
        return employeeService.getAll().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
