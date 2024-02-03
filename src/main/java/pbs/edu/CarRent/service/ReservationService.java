package pbs.edu.CarRent.service;


import pbs.edu.CarRent.model.Employee;
import pbs.edu.CarRent.model.Reservation;
import pbs.edu.CarRent.model.ReservationState;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ReservationService {

    List<Reservation> getReservations();
    List<Reservation> getReservationBySalonStart(Long salonId);
    List<Reservation> getReservationBySalonEnd(Long salonId);
    List<Reservation> getReservationByUser(Long userId);
    Optional<Reservation> addReservation(Reservation reservation);
    Optional<Reservation> getReservationById(Long id);
    Optional<Reservation> updateReservationStatus(Long id, ReservationState reservationState);
    Optional<Reservation> updateEndDate(Long id, Date end_date);
    Optional<Reservation> endReservation(Long id, Employee employee);

    void deleteReservation(Long id);
}
