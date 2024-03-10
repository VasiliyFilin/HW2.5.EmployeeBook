package pro.sky.employeebook.controller;

import org.springframework.web.bind.annotation.*;
import pro.sky.employeebook.model.Employee;
import pro.sky.employeebook.service.DepartmentService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService service;

    public DepartmentController(DepartmentService service) {
        this.service = service;
    }

    @GetMapping("/{id}/employees")
    public Map<String, Employee> findAllByDepartment(@PathVariable int id) {
        return service.findAllByDepartment(id);
    }
    @GetMapping("/{id}/salary/sum")
    public Double salarySumByDepartment(@PathVariable int id) {
        return service.salarySumByDepartment(id);
    }
    @GetMapping("/{id}/salary/max")
    public int findMaxSalary(@PathVariable int id) {
        return service.findMaxSalary(id);
    }
    @GetMapping("/{id}/salary/min")
    public int findMinSalary(@PathVariable int id) {
        return service.findMinSalary(id);
    }
    @GetMapping("/employees")
    public Map<Integer, List<Employee>> groupByDepartments() {
        return service.groupByDepartments();
    }
}
