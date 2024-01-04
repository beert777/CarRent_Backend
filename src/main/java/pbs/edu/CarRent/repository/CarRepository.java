package pbs.edu.CarRent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pbs.edu.CarRent.model.Car;
import pbs.edu.CarRent.model.Salon;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findCarsBySalon(Salon salon);

    List<Car> findCarsBySalonAndAvailable(Salon salon, boolean available);

    List<Car> findCarsByAvailable(boolean available);
}
