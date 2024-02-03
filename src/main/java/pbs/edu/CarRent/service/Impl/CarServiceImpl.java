package pbs.edu.CarRent.service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pbs.edu.CarRent.model.Car;
import pbs.edu.CarRent.model.Salon;
import pbs.edu.CarRent.payload.request.ResponseCode;
import pbs.edu.CarRent.payload.request.ResultInfo;
import pbs.edu.CarRent.repository.CarRepository;
import pbs.edu.CarRent.service.CarService;
import pbs.edu.CarRent.service.FileUploadService;
import pbs.edu.CarRent.service.SalonService;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final FileUploadService fileUploadService;
    private final SalonService salonService;

    @Override
    public List<Car> getCars() {
        return carRepository.findAll();
    }

    @Override
    public List<Car> getCarsBySalon(Long salonId) {
        Salon salon = salonService.getSalonById(salonId).orElseThrow(NoSuchElementException::new);
        return carRepository.findCarsBySalon(salon);
    }

    @Override
    public List<Car> getAvailableCarsBySalon(Long salonId) {
        Salon salon = salonService.getSalonById(salonId).orElseThrow(NoSuchElementException::new);
        return carRepository.findCarsBySalonAndAvailable(salon, true);
    }

    @Override
    public List<Car> getAvailableCars() {
        return carRepository.findCarsByAvailable(true);
    }

    @Override
    public Optional<Car> getCarById(Long id) {
        return carRepository.findById(id);
    }


    @Override
    public Optional<Car> addNewCar(Car car, MultipartFile file) {

        ResultInfo resultInfo;
        String fileName = file.getOriginalFilename();

        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                // Creating the directory to store file
                String rootPath = System.getProperty("user.home");
                File dir = new File(rootPath + File.separator + "tmpFiles");
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                // Create the file on server
                File serverFile = new File(dir.getAbsolutePath() + File.separator + fileName);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
                System.out.println("Server File Location=" + serverFile.getAbsolutePath());

                resultInfo = fileUploadService.fileUpload(serverFile.getAbsolutePath(), file.getOriginalFilename());
                serverFile.delete();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if(resultInfo.getResultCode().equals(ResponseCode.SUCCESS))
                car.setImageLink(resultInfo.getPathFolder() + "/" + file.getOriginalFilename());
            else {
                throw new RuntimeException();
            }
        }
        Salon salon = salonService.getSalonById(car.getSalon().getId()).orElseThrow(NoSuchElementException::new);
        car.setSalon(salon);
        return Optional.of(carRepository.save(car));
    }
    @Override
    public Optional<Car> updateCar(Long id, Car car) {
        Car newCar = carRepository.findById(id).orElseThrow(NoSuchElementException::new);
        if(car.getAvailable() != null) {
            newCar.setAvailable(car.getAvailable());
        }
        if(car.getSalon() != null) {
            newCar.setSalon(car.getSalon());
        }
        if(car.getModel() != null) {
            newCar.setModel(car.getModel());
        }
        if(car.getBrand() != null) {
            newCar.setBrand(car.getBrand());
        }
        if(car.getDescription() != null) {
            newCar.setDescription(car.getDescription());
        }
        if(car.getImageLink() != null) {
            newCar.setImageLink(car.getImageLink());
        }
        return Optional.of(carRepository.save(newCar));
    }

    @Override
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }
}

