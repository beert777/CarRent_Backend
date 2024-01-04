package pbs.edu.CarRent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pbs.edu.CarRent.model.Employee;
import pbs.edu.CarRent.model.Salon;
import pbs.edu.CarRent.model.User;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findAllBySalonEmployee(Salon salon);

    Optional<Employee> findByUser(User user);
}
