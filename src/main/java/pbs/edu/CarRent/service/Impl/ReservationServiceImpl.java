package pbs.edu.CarRent.service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pbs.edu.CarRent.model.*;
import pbs.edu.CarRent.repository.ReservationRepository;
import pbs.edu.CarRent.service.CarService;
import pbs.edu.CarRent.service.UserService;
import pbs.edu.CarRent.service.ReservationService;
import pbs.edu.CarRent.service.SalonService;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
@AllArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;

    private final CarService carService;

    private final SalonService salonService;

    private final UserService userService;

    @Override
    public List<Reservation> getReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public List<Reservation> getReservationBySalonStart(Long salonId) {
        Salon salon = salonService.getSalonById(salonId).orElseThrow(NoSuchElementException::new);
        return reservationRepository.findReservationsBySalonStart(salon);
    }

    @Override
    public List<Reservation> getReservationBySalonEnd(Long salonId) {
        Salon salon = salonService.getSalonById(salonId).orElseThrow(NoSuchElementException::new);
        return reservationRepository.findReservationsBySalonEnd(salon);
    }

    @Override
    public List<Reservation> getReservationByUser(Long userId) {
        User user = userService.getUserById(userId).orElseThrow(NoSuchElementException::new);
        return reservationRepository.findReservationsByUserReservation(user);
    }

    @Override
    public Optional<Reservation> addReservation(Reservation reservation) {
        Date startDate = reservation.getStartDate();
        Date endDate = reservation.getEndDate();
        List<Reservation> reservations = reservationRepository.findReservationsByCarReservation(reservation.getCarReservation());
        for(Reservation res: reservations){
            Boolean fl=false;
            if(startDate.equals(res.getStartDate())||(startDate.after(res.getStartDate())&&startDate.before(res.getEndDate())))
                fl=true;
            else if(endDate.equals(res.getEndDate())||(endDate.after(res.getStartDate())&&endDate.before(res.getEndDate())))
                fl=true;
            else if((startDate.before(res.getStartDate()))&&(endDate.before(res.getEndDate())||endDate.after(res.getEndDate())))
                fl=true;
            else if((startDate.after(res.getStartDate())&&startDate.before(res.getEndDate()))&&endDate.after(res.getEndDate()))
                fl=true;

            if (fl==true)
                throw new RuntimeException("Samochod nie dostepny w podanej dacie");

        }
        return Optional.of(reservationRepository.save(reservation));
    }

    @Override
    public Optional<Reservation> getReservationById(Long id) {
        return reservationRepository.findById(id);
    }

    @Override
    public Optional<Reservation> updateReservationStatus(Long id, ReservationState reservationState) {
        Reservation reservation=reservationRepository.findById(id).orElseThrow(NoSuchElementException::new);
        reservation.setState(reservationState);
        return Optional.of(reservationRepository.save(reservation));
    }

    @Override
    public Optional<Reservation> updateEndDate(Long id, Date end_date) {
        Reservation reservation=reservationRepository.findById(id).orElseThrow(NoSuchElementException::new);
        reservation.setEndDate(end_date);
        return Optional.of(reservationRepository.save(reservation));
    }
    @Override
    public Optional<Reservation> startReservation(Long id){
        Reservation reservation=reservationRepository.findReservationById(id);
        if(reservation.getCarReservation().getAvailable()==true)
            carService.setCarStatus(reservation.getCarReservation().getId(), false);
        else
            throw new RuntimeException("Samochód nie jest dostepny w systemie!");
        reservation.setState(ReservationState.IN_PROGRESS);
        return Optional.of(reservationRepository.save(reservation));
    }
    @Override
    public Optional<Reservation> cancelReservation(Long id){
        Reservation reservation=reservationRepository.findReservationById(id);
        reservation.setState(ReservationState.CANCELED);
        return Optional.of(reservationRepository.save(reservation));
    }
    @Override
    public Optional<Reservation> endReservation(Long id, Employee employee) {
        Reservation reservation=reservationRepository.findReservationById(id);
        reservation.setEndDate(java.sql.Date.valueOf(LocalDate.now()));
        reservation.setEmployeeEnd(employee);
        reservation.setState(ReservationState.DONE);
        carService.setCarStatus(reservation.getCarReservation().getId(), true);
        return Optional.of(reservationRepository.save(reservation));
    }

    @Override
    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }
}

