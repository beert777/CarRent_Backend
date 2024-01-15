package pbs.edu.CarRent.controller.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pbs.edu.CarRent.controller.EmployeeController;
import pbs.edu.CarRent.model.Employee;
import pbs.edu.CarRent.service.EmployeeService;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
@Slf4j
public class EmployeeControllerImpl implements EmployeeController {

    private final EmployeeService employeeService;

    @Override
    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getEmployees() {
        return ResponseEntity.ok(employeeService.getEmployees());
    }

    @Override
    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        return ResponseEntity.of(employeeService.getEmployeeById(id));
    }

    @Override
    @GetMapping("/salons/{salonId}/employees")
    public ResponseEntity<List<Employee>> getEmployeesBySalonId(@PathVariable Long salonId) {
        return ResponseEntity.ok(employeeService.getEmployeesBySalonId(salonId));
    }

    @Override
    @PostMapping("/employees")
    public ResponseEntity<Employee> addNewEmployee(@RequestBody Employee employee) {
        return ResponseEntity.of(employeeService.addNewEmployee(employee));
    }

    @Override
    @DeleteMapping("/employees/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }
}
