package pro.sky.employeebook;

import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final List<Employee> employeeList;
    public EmployeeServiceImpl(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }
    private final int maxEmployees = 10;
    @Override
    public Employee add(Employee employee) {
        if (employeeList.contains(employee)) {
            throw new EmployeeAlreadyAddedException("Сотрудник уже был добавлен");
        }
        if (employeeList.size() >= maxEmployees) {
            throw new EmployeeStorageIsFullException("Хранилище сотрудников заполнено");
        }
        employeeList.add(employee);
        return employee;
    }

    @Override
    public Employee remove(Employee employee) {
        if (employeeList.contains(employee)) {
            employeeList.remove(employee);
            return employee;
        } else {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
    }

    @Override
    public Employee find(Employee employee) {
        if (employeeList.contains(employee)) {
            return employee;
        } else {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
    }
}
