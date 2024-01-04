package pbs.edu.CarRent.service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pbs.edu.CarRent.model.Salon;
import pbs.edu.CarRent.repository.SalonRepository;
import pbs.edu.CarRent.service.SalonService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SalonServiceImpl implements SalonService {

    private final SalonRepository salonRepository;

    @Override
    public Optional<Salon> getSalonById(Long id) {
        return salonRepository.findById(id);
    }

    @Override
    public List<Salon> getSalons() {
        return salonRepository.findAll();
    }

    @Override
    public Optional<Salon> updateSalon(Long id, Salon salon) {
        Salon newSalon = salonRepository.findById(id).orElseThrow(NoSuchElementException::new);
        if(salon.getCity() != null) {
            newSalon.setCity(salon.getCity());
        }
        if(salon.getAddress() != null) {
            newSalon.setAddress(salon.getAddress());
        }
        return Optional.of(salonRepository.save(newSalon));
    }

    @Override
    public Optional<Salon> addNewSalon(Salon salon) {
        return Optional.of(salonRepository.save(salon));
    }

    @Override
    public void deleteSalon(Long id) {
        salonRepository.deleteById(id);
    }
}

