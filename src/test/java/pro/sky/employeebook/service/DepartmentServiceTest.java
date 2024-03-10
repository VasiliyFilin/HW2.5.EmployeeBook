package pro.sky.employeebook.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.employeebook.model.Employee;


import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {
    private final Map<String, Employee> EMPLOYEES = Map.of(
            "Ivan Ivanov", new Employee("Ivan", "Ivanov", 10000, 3),
            "Sergey Ivanov", new Employee("Sergey", "Ivanov", 11000, 3),
            "Anna Ivanova", new Employee("Anna", "Ivanova", 50000, 2),
            "Petr Petrov", new Employee("Petr", "Petrov", 100000, 1),
            "Dmitry Smirnov", new Employee("Dmitry", "Smirnov", 30000, 4)
    );

    @Mock
    EmployeeService employeeService;
    @InjectMocks
    DepartmentService out;

    @BeforeEach
    void setUp() {
        when(employeeService.getAll()).thenReturn(Collections.unmodifiableCollection(EMPLOYEES.values()));
    }

    @Test
    void shouldReturnCorrectResultOfFindingAllByDepartment() {
        Map<String, Employee> act = out.findAllByDepartment(3);
        Map<String, Employee> exp = Map.of("Ivan Ivanov", new Employee("Ivan", "Ivanov", 10000, 3),
                "Sergey Ivanov", new Employee("Sergey", "Ivanov", 11000, 3));
        assertEquals(exp, act);
    }

    @Test
    void shouldReturnCorrectResultOfSalarySummationByDepartment() {
        var act = out.salarySumByDepartment(3);
        var exp = 21000;
        assertEquals(exp, act);
    }

    @Test
    void shouldReturnCorrectResultOfFindingMaxSalary() {
        var act = out.findMaxSalary(3);
        var exp = 11000;
        assertEquals(exp, act);
    }

    @Test
    void shouldReturnCorrectResultOfFindingMinSalary() {
        var act = out.findMinSalary(3);
        var exp = 10000;
        assertEquals(exp, act);
    }

    @Test
    void shouldReturnCorrectResultOfGroupingByDepartments() {
        Map<Integer, List<Employee>> act = out.groupByDepartments();
        Map<Integer, List<Employee>> exp = Map.of(
                1, List.of(new Employee("Petr", "Petrov", 100000, 1)),
                2, List.of(new Employee("Anna", "Ivanova", 50000, 2)),
                3, List.of(new Employee("Ivan", "Ivanov", 10000, 3),
                        new Employee("Sergey", "Ivanov", 11000, 3)),
                4, List.of(new Employee("Dmitry", "Smirnov", 30000, 4)));
        assertEquals(act.size(), exp.size());
        assertTrue(act.get(2).containsAll(exp.get(2)));
        assertTrue(act.get(3).containsAll(exp.get(3)));
        assertTrue(act.get(4).containsAll(exp.get(4)));
    }
}