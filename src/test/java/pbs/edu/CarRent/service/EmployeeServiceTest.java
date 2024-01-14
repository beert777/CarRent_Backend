package pbs.edu.CarRent.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pbs.edu.CarRent.model.Employee;
import pbs.edu.CarRent.model.Salon;
import pbs.edu.CarRent.model.User;
import pbs.edu.CarRent.repository.EmployeeRepository;
import pbs.edu.CarRent.service.EmployeeService;
import pbs.edu.CarRent.service.Impl.EmployeeServiceImpl;
import pbs.edu.CarRent.service.SalonService;
import pbs.edu.CarRent.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private SalonService salonService;

    @Mock
    private UserService userService;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetEmployeeById(){
        Long employeeId = 1L;
        Employee employee = new Employee();
        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));
        Optional<Employee> result = employeeService.getEmployeeById(employeeId);
        assertEquals(Optional.of(employee), result);
    }
    @Test
    public void testGetEmployeeByUserId(){
        Long userId = 1L;
        User user = new User();
        when(userService.getUserById(userId)).thenReturn(Optional.of(user));
        Employee employee = new Employee();
        when(employeeRepository.findByUser(user)).thenReturn(Optional.of(employee));
        Optional<Employee> result = employeeService.getEmployeeByUserId(userId);
        assertEquals(Optional.of(employee), result);
    }
    @Test
    public void testGetEmployees(){
        List<Employee> employees = new ArrayList<Employee>();
        when(employeeRepository.findAll()).thenReturn(employees);
        List<Employee> result = employeeService.getEmployees();
        assertEquals(employees, result);
    }
    @Test
    public void testGetEmployeesBySalonId(){
        Long salonId = 1L;
        Salon salon = new Salon();
        when(salonService.getSalonById(salonId)).thenReturn(Optional.of(salon));
        List<Employee> employees = new ArrayList<Employee>();
        when(employeeRepository.findAllBySalonEmployee(salon)).thenReturn(employees);
        List<Employee> result = employeeService.getEmployeesBySalonId(salonId);
        assertEquals(employees, result);
    }
    @Test
    public void testAddNewEmployee(){
        Employee employee = new Employee();
        when(employeeRepository.save(employee)).thenReturn(employee);
        Optional<Employee> result = employeeService.addNewEmployee(employee);
        assertEquals(Optional.of(employee), result);
    }
    @Test
    public void testDeleteEmployee(){
        Long employeeId = 1L;
        employeeService.deleteEmployee(employeeId);
        verify(employeeRepository).deleteById(employeeId);
    }

}
