package pro.sky.employeebook.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pro.sky.employeebook.exception.EmployeeAlreadyAddedException;
import pro.sky.employeebook.exception.EmployeeNotFoundException;
import pro.sky.employeebook.exception.InvalidInputException;
import pro.sky.employeebook.model.Employee;


import java.util.HashMap;
import java.util.Map;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceTest {
    EmployeeService out;

    @BeforeEach
    void setUp() throws InvalidInputException {
        out = new EmployeeService();
        out.add(new Employee("Ivan", "Ivanov", 10000, 3));
        out.add(new Employee("Sergey", "Ivanov", 10000, 3));
        out.add(new Employee("Anna", "Ivanova", 50000, 2));
        out.add(new Employee("Petr", "Petrov", 100000, 1));
        out.add(new Employee("Dmitry", "Smirnov", 30000, 4));
    }
    public static Stream<Arguments> argsForCorrectResultOfAdditionTest() {
        return Stream.of(Arguments.of("Boris", "Abramov", 40000, 4));
    }

    public static Stream<Arguments> argsForInvalidInputTest() {
        return Stream.of(Arguments.of("4", "40000", 40000, 4));
    }

    public static Stream<Arguments> argsForEmployeeAlreadyAddedTest() {
        return Stream.of(Arguments.of("Ivan", "Ivanov", 10000, 3));
    }

    public static Stream<Arguments> argsForCorrectResultOfRemovingTest() {
        return Stream.of(Arguments.of(("Dmitry"), "Smirnov", 30000, 4));
    }

    public static Stream<Arguments> argsForEmployeeNotFoundTest() {
        return Stream.of(Arguments.of(("Koschey"), "Bessmertny", 666, 666));
    }

    public static Stream<Arguments> argsForCorrectResultOfFindingTest() {
        return Stream.of(Arguments.of(("Petr"), "Petrov", 100000, 1));
    }
    @ParameterizedTest
    @MethodSource("argsForCorrectResultOfAdditionTest")
    void shouldReturnCorrectResultOfAdditionTest(String firstName, String lastName, int salary, int department)
            throws InvalidInputException {
        Employee employee = new Employee(firstName, lastName, salary, department);
        out.add(employee);
    }

    @ParameterizedTest
    @MethodSource("argsForInvalidInputTest")
    void ShouldThrowInvalidInputException(String firstName, String lastName, int salary, int department) {
        Employee employee = new Employee(firstName, lastName, salary, department);
        assertThrows(InvalidInputException.class, () -> out.add(employee));
    }

    @ParameterizedTest
    @MethodSource("argsForEmployeeAlreadyAddedTest")
    void ShouldThrowEmployeeAlreadyAddedException(String firstName,
                                                        String lastName,
                                                        int salary,
                                                        int department) {
        Employee employee = new Employee(firstName, lastName, salary, department);
        assertThrows(EmployeeAlreadyAddedException.class, () -> out.add(employee));
    }

    @ParameterizedTest
    @MethodSource("argsForCorrectResultOfRemovingTest")
    void ReturnCorrectResultOfRemovingTest(String firstName, String lastName, int salary, int department)
            throws InvalidInputException {
        Employee employee = new Employee(firstName, lastName, salary, department);
        out.remove(employee);
    }

    @ParameterizedTest
    @MethodSource("argsForEmployeeNotFoundTest")
    void ShouldThrowEmployeeNotFoundExceptionInRemoveMethod(String firstName,
                                                                  String lastName,
                                                                  int salary,
                                                                  int department) {
        Employee employee = new Employee(firstName, lastName, salary, department);
        assertThrows(EmployeeNotFoundException.class, () -> out.remove(employee));
    }

    @ParameterizedTest
    @MethodSource("argsForCorrectResultOfFindingTest")
    void shouldReturnCorrectResultOfFindingTest(String firstName, String lastName, int salary, int department)
            throws InvalidInputException {
        Employee employee = new Employee(firstName, lastName, salary, department);
        assertEquals(out.find(employee), employee);
    }

    @ParameterizedTest
    @MethodSource("argsForEmployeeNotFoundTest")
    void ShouldThrowEmployeeNotFoundExceptionInFindMethod(String firstName,
                                                                String lastName,
                                                                int salary,
                                                                int department) {
        Employee employee = new Employee(firstName, lastName, salary, department);
        assertThrows(EmployeeNotFoundException.class, () -> out.find(employee));
    }

    @Test
    void ShouldReturnCorrectResultOfGettingAllTest() {
        Map<String, Employee> act = out.getAll().stream()
                .collect(Collectors.toMap(Employee::getFullName, employee -> employee));
        Map<String, Employee> exp = new HashMap<>();

        exp.put("Ivan Ivanov", new Employee("Ivan", "Ivanov", 10000, 3));
        exp.put("Sergey Ivanov", new Employee("Sergey", "Ivanov", 10000, 3));
        exp.put("Anna Ivanova", new Employee("Anna", "Ivanova", 50000, 2));
        exp.put("Petr Petrov", new Employee("Petr", "Petrov", 100000, 1));
        exp.put("Dmitry Smirnov", new Employee("Dmitry", "Smirnov", 30000, 4));

        assertEquals(act.size(), exp.size());
        assertEquals(act.get("Ivan Ivanov"), exp.get("Ivan Ivanov"));
        assertEquals(act.get("Anna Ivanova"), exp.get("Anna Ivanova"));
        assertEquals(act.get("Dmitry Smirnov"), exp.get("Dmitry Smirnov"));
    }
}