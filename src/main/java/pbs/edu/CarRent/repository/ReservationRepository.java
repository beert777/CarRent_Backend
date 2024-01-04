package pbs.edu.CarRent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pbs.edu.CarRent.model.Reservation;
import pbs.edu.CarRent.model.Salon;
import pbs.edu.CarRent.model.User;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findReservationsBySalonStart(Salon salon);

    List<Reservation> findReservationsByUserReservation(User user);

    List<Reservation> findReservationsBySalonEnd(Salon salon);

    Reservation findReservationById(Long id);
}

