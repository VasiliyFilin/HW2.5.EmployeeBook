package pro.sky.employeebook.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.employeebook.model.Employee;
import pro.sky.employeebook.service.DepartmentService;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService service;

    public DepartmentController(DepartmentService service) {
        this.service = service;
    }

    @GetMapping("/max-salary")
    public ResponseEntity<Employee> findMaxSalary(@RequestParam int departmentId) {
        return ResponseEntity.of(service.findMaxSalary(departmentId));
    }
    @GetMapping("/min-salary")
    public ResponseEntity<Employee> findMinSalary(@RequestParam int departmentId) {
        return ResponseEntity.of(service.findMinSalary(departmentId));
    }
    @GetMapping(value = "/all", params = "departmentId")
    public Collection<Employee> findAllByDepartment(@RequestParam int departmentId) {
        return service.findAllByDepartment(departmentId);
    }
    @GetMapping("/all")
    public Map<Integer, List<Employee>> groupByDepartments() {
        return service.groupByDepartments();
    }
}
