package pbs.edu.CarRent.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import pbs.edu.CarRent.model.Car;
import pbs.edu.CarRent.model.Salon;
import pbs.edu.CarRent.repository.CarRepository;
import pbs.edu.CarRent.service.CarService;
import pbs.edu.CarRent.service.Impl.CarServiceImpl;
import pbs.edu.CarRent.service.SalonService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CarServiceTest {

    @Mock
    private CarRepository carRepository;
    @Mock
    private SalonService salonService;
    @InjectMocks
    private CarServiceImpl carService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetCars(){
        List<Car> cars = new ArrayList<>();
        when(carRepository.findAll()).thenReturn(cars);
        List<Car> result = carService.getCars();
        assertEquals(cars, result);
    }
    @Test
    public void testGetCarsBySalon() {
        Long salonId = 1L;
        Salon salon = new Salon();
        when(salonService.getSalonById(salonId)).thenReturn(Optional.of(salon));
        List<Car> cars = new ArrayList<>();
        when(carRepository.findCarsBySalon(salon)).thenReturn(cars);
        List<Car> result = carService.getCarsBySalon(salonId);
        assertEquals(cars, result);
    }
    @Test
    public void testGetAvailableCarsBySalon(){
        Long salonId = 1L;
        Salon salon = new Salon();
        when(salonService.getSalonById(salonId)).thenReturn(Optional.of(salon));
        List<Car> cars = new ArrayList<>();
        when(carRepository.findCarsBySalonAndAvailable(salon, true)).thenReturn(cars);
        List<Car> result = carService.getAvailableCarsBySalon(salonId);
        assertEquals(cars, result);
    }
    @Test
    public void testGetAvailableCars(){
        List<Car> cars = new ArrayList<Car>();
        when(carRepository.findCarsByAvailable(true)).thenReturn(cars);
        List<Car> result = carService.getAvailableCars();
        assertEquals(cars, result);
    }
    @Test
    public void testGetCarById(){
        Long carId = 1L;
        Car car = new Car();
        when(carRepository.findById(carId)).thenReturn(Optional.of(car));
        Optional<Car> result = carService.getCarById(carId);
        assertEquals(Optional.of(car), result);
    }
    /* Dla potomnych
    @Test
    public void testAddNewCar(){
        //
    }*/
    @Test
    public void testUpdateCar(){
        Long carId = 1L;
        Car car = new Car();
        when(carRepository.findById(carId)).thenReturn(Optional.of(car));
        Car newCar = new Car();
        when(carRepository.save(car)).thenReturn(newCar);
        Car result = new Car();
        assertEquals(newCar, result);
    }
    @Test
    public void testDeleteCar(){
        Long carId = 1L;
        carService.deleteCar(carId);
        verify(carRepository).deleteById(carId);
    }
}
