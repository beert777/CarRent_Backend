package pbs.edu.CarRent.service;


import pbs.edu.CarRent.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    Optional<Employee> getEmployeeById(Long id);

    Optional<Employee> getEmployeeByUserId(Long id);

    List<Employee> getEmployees();

    List<Employee> getEmployeesBySalonId(Long salonId);

    Optional<Employee> addNewEmployee(Employee employee);

    void deleteEmployee(Long id);
}

