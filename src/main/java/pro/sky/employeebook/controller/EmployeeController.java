package pro.sky.employeebook.controller;

import org.apache.coyote.BadRequestException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.employeebook.model.Employee;
import pro.sky.employeebook.service.EmployeeService;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {
    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping(path = "/add")
    public Employee addEmployee(@RequestParam("firstName") String firstName,
                                @RequestParam("lastName") String lastName,
                                @RequestParam("salary") int salary,
                                @RequestParam("department") int department) throws BadRequestException {
        Employee employee = new Employee(firstName, lastName, salary, department);
        service.add(employee);
        return employee;
    }

    @GetMapping(path = "/remove")
    public Employee removeEmployee(@RequestParam("firstName") String firstName,
                                   @RequestParam("lastName") String lastName,
                                   @RequestParam("salary") int salary,
                                   @RequestParam("department") int department) throws BadRequestException {
        Employee employee = new Employee(firstName, lastName, salary, department);
        service.remove(employee);
        return employee;
    }

    @GetMapping(path = "/find")
    public Employee findEmployee(@RequestParam("firstName") String firstName,
                                 @RequestParam("lastName") String lastName,
                                 @RequestParam("salary") int salary,
                                 @RequestParam("department") int department) throws BadRequestException {
        Employee employee = new Employee(firstName, lastName, salary, department);
        service.find(employee);
        return employee;
    }

}