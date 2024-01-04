package pbs.edu.CarRent.service;

import org.springframework.web.multipart.MultipartFile;
import pbs.edu.CarRent.model.Car;

import java.util.List;
import java.util.Optional;

public interface CarService {

    List<Car> getCars();

    List<Car> getCarsBySalon(Long salonId);

    List<Car> getAvailableCarsBySalon(Long salonId);

    List<Car> getAvailableCars();

    Optional<Car> getCarById(Long id);

    Optional<Car> addNewCar(Car car, MultipartFile fileName);
    Optional<Car> updateCar(Long id, Car car);

    void deleteCar(Long id);
}
