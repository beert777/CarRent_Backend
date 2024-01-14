package pbs.edu.CarRent.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pbs.edu.CarRent.model.*;
import pbs.edu.CarRent.repository.ReservationRepository;
import pbs.edu.CarRent.service.Impl.ReservationServiceImpl;
import pbs.edu.CarRent.service.SalonService;
import pbs.edu.CarRent.service.UserService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private SalonService salonService;

    @Mock
    private UserService userService;

    @InjectMocks
    private ReservationServiceImpl reservationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testGetReservations(){
        List<Reservation> reservations = new ArrayList<Reservation>();
        when(reservationRepository.findAll()).thenReturn(reservations);
        List<Reservation> result = reservationService.getReservations();
        assertEquals(reservations, result);
    }
    @Test
    public void testGetReservationBySalonStart(){
        Long salonId = 1L;
        Salon salon = new Salon();
        when(salonService.getSalonById(salonId)).thenReturn(Optional.of(salon));
        List<Reservation> reservations = new ArrayList<Reservation>();
        when(reservationRepository.findReservationsBySalonStart(salon)).thenReturn(reservations);
        List<Reservation> result = reservationService.getReservationBySalonStart(salonId);
        assertEquals(reservations, result);
    }
    @Test
    public void testGetReservationBySalonEnd(){
        Long salonId = 1L;
        Salon salon = new Salon();
        when(salonService.getSalonById(salonId)).thenReturn(Optional.of(salon));
        List<Reservation> reservations = new ArrayList<Reservation>();
        when(reservationRepository.findReservationsBySalonEnd(salon)).thenReturn(reservations);
        List<Reservation> result = reservationService.getReservationBySalonEnd(salonId);
        assertEquals(reservations, result);
    }
    @Test
    public void testGetReservationByUser(){
        Long userId = 1L;
        User user = new User();
        when(userService.getUserById(userId)).thenReturn(Optional.of(user));
        List<Reservation> reservations = new ArrayList<Reservation>();
        when(reservationRepository.findReservationsByUserReservation(user)).thenReturn(reservations);
        List<Reservation> result = reservationService.getReservationByUser(userId);
        assertEquals(reservations, result);
    }
    @Test
    public void testAddReservation(){
        Reservation reservation = new Reservation();
        when(reservationRepository.save(reservation)).thenReturn(reservation);
        Optional<Reservation> result = reservationService.addReservation(reservation);
        assertEquals(Optional.of(reservation), result);
    }
    @Test
    public void testGetReservationById(){
        Long reservationId = 1L;
        Reservation reservation = new Reservation();
        when(reservationRepository.findById(reservationId)).thenReturn(Optional.of(reservation));
        Optional<Reservation> result = reservationService.getReservationById(reservationId);
        assertEquals(Optional.of(reservation), result);
    }
    @Test
    public void testUpdateReservationStatus(){
        Long reservationId = 1L;
        Reservation reservation = new Reservation();
        when(reservationRepository.findById(reservationId)).thenReturn(Optional.of(reservation));
        ReservationState state = ReservationState.TEST;
        reservation.setState(state);
        when(reservationRepository.save(reservation)).thenReturn(reservation);
        Optional<Reservation> result = reservationService.updateReservationStatus(reservationId, state);
        assertEquals(Optional.of(reservation), result);
    }
    @Test
    public void testUpdateEndDate(){
        Long reservationId = 1L;
        Reservation reservation = new Reservation();
        when(reservationRepository.findById(reservationId)).thenReturn(Optional.of(reservation));
        Date date = new Date();
        reservation.setEndDate(date);
        when(reservationRepository.save(reservation)).thenReturn(reservation);
        Optional<Reservation> result = reservationService.updateEndDate(reservationId, date);
        assertEquals(Optional.of(reservation), result);
    }
    @Test
    public void testEndReservation(){
        Long reservationId = 1L;
        Employee employee = new Employee();
        Reservation reservation = new Reservation();
        when(reservationRepository.findReservationById(reservationId)).thenReturn(reservation);
        reservation.setEndDate(java.sql.Date.valueOf(LocalDate.now()));
        reservation.setEmployeeEnd(employee);
        reservation.setState(ReservationState.DONE);
        when(reservationRepository.save(reservation)).thenReturn(reservation);
        Optional<Reservation> result = reservationService.endReservation(reservationId, employee);
        assertEquals(Optional.of(reservation), result);
    }
    @Test
    public void testDeleteReservation(){
        Long reservationId = 1L;
        reservationService.deleteReservation(reservationId);
        verify(reservationRepository).deleteById(reservationId);
    }
}
