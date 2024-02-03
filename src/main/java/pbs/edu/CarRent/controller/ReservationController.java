package pbs.edu.CarRent.controller;

import org.springframework.http.ResponseEntity;
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
    ResponseEntity<Reservation> endReservation(Long id, Employee employee);
    void deleteReservation(Long id);
}

