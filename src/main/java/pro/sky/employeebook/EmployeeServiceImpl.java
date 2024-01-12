package pro.sky.employeebook;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final List<Employee> employeeList;

    public EmployeeServiceImpl(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    private final int maxEmployees = 2;

    @Override
    public Employee add(Employee employee) throws EmployeeAlreadyAddedException, EmployeeStorageIsFullException {
        if (employeeList.size() < maxEmployees) {
            if (employeeList.contains(employee)) {
                throw new EmployeeAlreadyAddedException("EmployeeAlreadyAdded");
            }
            employeeList.add(employee);
            return employee;
        } else {
            throw new EmployeeStorageIsFullException("EmployeeStorageIsFull");
        }
    }

    @Override
    public Employee remove(Employee employee) throws EmployeeNotFoundException {
        if (employeeList.contains(employee)) {
            employeeList.remove(employee);
            return employee;
        } else {
            throw new EmployeeNotFoundException("EmployeeNotFound");
        }
    }

    @Override
    public Employee find(Employee employee) throws EmployeeNotFoundException {
        if (employeeList.contains(employee)) {
            return employee;
        } else {
            throw new EmployeeNotFoundException("EmployeeNotFound");
        }
    }
    public List<Employee> listEmployee() {
        return employeeList;
    }
}
