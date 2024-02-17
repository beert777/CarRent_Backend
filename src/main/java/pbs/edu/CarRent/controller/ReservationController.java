package pbs.edu.CarRent.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pbs.edu.CarRent.model.Employee;
import pbs.edu.CarRent.model.Reservation;
import pbs.edu.CarRent.model.ReservationState;

import java.util.Date;
import java.util.List;

public interface ReservationController {
    ResponseEntity<List<Reservation>> getReservations();
    ResponseEntity<List<Reservation>> getReservationBySalonStart(Long salonId);
    ResponseEntity<List<Reservation>> getReservationBySalonEnd(Long salonId);
    ResponseEntity<List<Reservation>> getReservationByUser(Long userId);
    ResponseEntity<Reservation> getReservation(Long id);
    ResponseEntity<Reservation> addReservation(Reservation reservation);
    ResponseEntity<Reservation> updateReservationStatus(Long id, ReservationState reservationState);
    ResponseEntity<Reservation> updateEndDate(Long id, Date end_date);
    ResponseEntity<Reservation> startReservation(Long id);
    ResponseEntity<Reservation> cancelReservation(Long id);
    ResponseEntity<Reservation> endReservation(Long id, Employee employee);
    void deleteReservation(Long id);
}

