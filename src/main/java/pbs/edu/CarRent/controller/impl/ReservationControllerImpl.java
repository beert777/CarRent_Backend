package pbs.edu.CarRent.controller.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pbs.edu.CarRent.controller.ReservationController;
import pbs.edu.CarRent.model.Employee;
import pbs.edu.CarRent.model.Reservation;
import pbs.edu.CarRent.model.ReservationState;
import pbs.edu.CarRent.service.ReservationService;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
@Slf4j
public class ReservationControllerImpl implements ReservationController {

    private final ReservationService reservationService;

    @Override
    @GetMapping("/reservations")
    public ResponseEntity<List<Reservation>> getReservations() {
        return ResponseEntity.ok(reservationService.getReservations());
    }

    @Override
    @GetMapping("/reservations/salon/start/{salonId}")
    public ResponseEntity<List<Reservation>> getReservationBySalonStart(@PathVariable Long salonId) {
        return ResponseEntity.ok(reservationService.getReservationBySalonStart(salonId));
    }

    @Override
    @GetMapping("/reservations/salon/end/{salonId}")
    public ResponseEntity<List<Reservation>> getReservationBySalonEnd(@PathVariable Long salonId) {
        return ResponseEntity.ok(reservationService.getReservationBySalonEnd(salonId));
    }

    @Override
    @GetMapping("/reservations/user/{userId}")
    public ResponseEntity<List<Reservation>> getReservationByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(reservationService.getReservationByUser(userId));
    }

    @Override
    @GetMapping("/reservation/{id}")
    public ResponseEntity<Reservation> getReservation(@PathVariable Long id) {
        return ResponseEntity.of(reservationService.getReservationById(id));
    }

    @Override
    @PostMapping("/reservation")
    public ResponseEntity<Reservation> addReservation(@RequestBody Reservation reservation) {
        return ResponseEntity.of(reservationService.addReservation(reservation));
    }

    @Override
    @PatchMapping("/reservation/status/{id}")
    public ResponseEntity<Reservation> updateReservationStatus(@PathVariable Long id, @RequestBody ReservationState reservationState) {
        return ResponseEntity.of(reservationService.updateReservationStatus(id, reservationState));
    }

    @Override
    @PatchMapping("/reservation/end/{id}")
    public ResponseEntity<Reservation> updateEndDate(@PathVariable Long id, @RequestBody Date end_date) {
        return ResponseEntity.of(reservationService.updateEndDate(id, end_date));
    }
    @Override
    @PatchMapping("/reservation/done/{id}")
    public ResponseEntity<Reservation> endReservation(@PathVariable Long id, @RequestBody Employee employee) {
        return ResponseEntity.of(reservationService.endReservation(id, employee));
    }

    @Override
    @DeleteMapping("/reservation/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReservation(Long id) {
        reservationService.deleteReservation(id);
    }
}
