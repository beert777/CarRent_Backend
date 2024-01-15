package pbs.edu.CarRent.controller.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pbs.edu.CarRent.controller.SalonController;
import pbs.edu.CarRent.model.Salon;
import pbs.edu.CarRent.service.SalonService;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
@Slf4j
public class SalonControllerImpl implements SalonController {

    private final SalonService salonService;

    @Override
    @GetMapping("/salons")
    public ResponseEntity<List<Salon>> getSalons() {
        return ResponseEntity.ok(salonService.getSalons());
    }

    @Override
    @GetMapping("/salons/{id}")
    public ResponseEntity<Salon> getSalonById(@PathVariable Long id) {
        return ResponseEntity.of(salonService.getSalonById(id));
    }

    @Override
    @PatchMapping("/salons/{id}")
    public ResponseEntity<Salon> updateSalon(@PathVariable Long id, @RequestBody Salon salon) {
        return ResponseEntity.of(salonService.updateSalon(id, salon));
    }

    @Override
    @PostMapping("/salons")
    public ResponseEntity<Salon> addNewSalon(@RequestBody Salon salon) {
        return ResponseEntity.of(salonService.addNewSalon(salon));
    }

    @Override
    @DeleteMapping("/salons/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSalon(@PathVariable Long id) {
        salonService.deleteSalon(id);
    }
}
