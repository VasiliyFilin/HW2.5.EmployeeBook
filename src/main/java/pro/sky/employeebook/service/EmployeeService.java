package pro.sky.employeebook.service;

import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import pro.sky.employeebook.exception.EmployeeAlreadyAddedException;
import pro.sky.employeebook.exception.EmployeeNotFoundException;
import pro.sky.employeebook.exception.EmployeeStorageIsFullException;
import pro.sky.employeebook.exception.InvalidInputException;
import pro.sky.employeebook.model.Employee;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.*;





@Service
public class EmployeeService {
    private static final int maxEmployees = 10;
    private final Map<String, Employee> employeeMap = new HashMap<>(maxEmployees);



    public Employee add(Employee employee) throws EmployeeAlreadyAddedException,
            EmployeeStorageIsFullException,
            InvalidInputException {
        validateInput(employee.getFirstName(), employee.getLastName());

    private String employeeKey(Employee employee) {
        return capitalize(employee.getFirstName()) + capitalize(employee.getLastName()) + employee.getDepartment();
    }

    public Employee add(Employee employee) throws
            EmployeeAlreadyAddedException,
            EmployeeStorageIsFullException,
            BadRequestException {
        checkStrings(employee.getFirstName(), employee.getLastName());

        if (employeeMap.size() < maxEmployees) {
            if (employeeMap.containsKey(employee.getFullName())) {
                throw new EmployeeAlreadyAddedException("EmployeeAlreadyAdded");
            }
            employeeMap.put(employee.getFullName(), employee);
            return employee;
        } else {
            throw new EmployeeStorageIsFullException("EmployeeStorageIsFull");
        }
    }



    public Employee remove(Employee employee) throws EmployeeNotFoundException, InvalidInputException {
        validateInput(employee.getFirstName(), employee.getLastName());
        if (employeeMap.containsKey(employee.getFullName())) {
            employeeMap.remove(employee.getFullName());

    public Employee remove(Employee employee) throws EmployeeNotFoundException, BadRequestException {
        checkStrings(employee.getFirstName(), employee.getLastName());
        if (employeeMap.containsKey(employeeKey(employee))) {
            employeeMap.remove(employeeKey(employee));

            return employee;
        } else {
            throw new EmployeeNotFoundException("EmployeeNotFound");
        }
    }


    public Employee find(Employee employee) throws EmployeeNotFoundException, InvalidInputException {
        validateInput(employee.getFirstName(), employee.getLastName());
        if (employeeMap.containsKey(employee.getFullName())) {

    public Employee find(Employee employee) throws EmployeeNotFoundException, BadRequestException {
        checkStrings(employee.getFirstName(), employee.getLastName());
        if (employeeMap.containsKey(employeeKey(employee))) {

            return employee;
        } else {
            throw new EmployeeNotFoundException("EmployeeNotFound");
        }
    }

    public Collection<Employee> getAll() {
        return Collections.unmodifiableCollection(employeeMap.values());
    }

    private void validateInput(String firstName, String lastName) throws InvalidInputException {
        if (!(isAlpha(firstName) & isAlpha(lastName))){
            throw new InvalidInputException();


    public void checkStrings(String firstName, String lastName) throws BadRequestException {
        if (!isAlpha(firstName) || !isAlpha(lastName)) {
            throw new BadRequestException();

        }
    }
}
