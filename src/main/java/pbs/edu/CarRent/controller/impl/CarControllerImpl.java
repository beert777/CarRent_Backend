package pbs.edu.CarRent.controller.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pbs.edu.CarRent.controller.CarController;
import pbs.edu.CarRent.model.Car;
import pbs.edu.CarRent.service.CarService;

import java.util.List;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
@Slf4j
public class CarControllerImpl implements CarController {

    private final CarService carService;
    private final ObjectMapper objectMapper;

    @Override
    @GetMapping("/cars")
    public ResponseEntity<List<Car>> getCars() {
        return ResponseEntity.ok(carService.getCars());
    }

    @Override
    @GetMapping("/cars/salon/{salonId}")
    public ResponseEntity<List<Car>> getCarsBySalon(@PathVariable Long salonId) {
        return ResponseEntity.ok(carService.getCarsBySalon(salonId));
    }

    @Override
    @GetMapping("/cars/salon/{salonId}/available")
    public ResponseEntity<List<Car>> getAvailableCarsBySalon(@PathVariable Long salonId) {
        return ResponseEntity.ok(carService.getAvailableCarsBySalon(salonId));
    }

    @Override
    @GetMapping("/cars/available")
    public ResponseEntity<List<Car>> getAvailableCars() {
        return ResponseEntity.ok(carService.getAvailableCars());
    }

    @Override
    @GetMapping("/cars/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id) {
        return ResponseEntity.of(carService.getCarById(id));
    }

    @Override
    @PatchMapping("/cars/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable Long id, @RequestBody Car car ) {
        return ResponseEntity.of(carService.updateCar(id, car));
    }

    @Override
    @PostMapping(path = "/cars", consumes = { "multipart/form-data" })
    public ResponseEntity<Car> addNewCar(@RequestPart("car") String carJson, @RequestPart("file") MultipartFile file) {
        Car car = null;
        try {
            car = objectMapper.readValue(carJson, Car.class);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e.getMessage());
        }

        return ResponseEntity.of(carService.addNewCar(car, file));
    }

    @Override
    @DeleteMapping("/cars/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
    }
}
