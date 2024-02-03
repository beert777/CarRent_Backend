package pbs.edu.CarRent.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pbs.edu.CarRent.model.Salon;
import pbs.edu.CarRent.repository.SalonRepository;
import pbs.edu.CarRent.service.Impl.SalonServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SalonServiceTest {

    @Mock
    private SalonRepository salonRepository;

    @InjectMocks
    private SalonServiceImpl salonService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetSalonById(){
        Long salonId = 1L;
        Salon salon = new Salon();
        when(salonRepository.findById(salonId)).thenReturn(Optional.of(salon));
        Optional<Salon> result = salonService.getSalonById(salonId);
        assertEquals(Optional.of(salon), result);
    }
    @Test
    public void testGetSalons(){
        List<Salon> salons = new ArrayList<Salon>();
        when(salonRepository.findAll()).thenReturn(salons);
        List<Salon> result = salonService.getSalons();
        assertEquals(salons, result);
    }
    @Test
    public void testUpdateSalon(){
        Long salonId = 1L;
        Salon salon = new Salon(2L, "Bydgoszcz", "Kaliskiego 13");
        Salon newSalon = new Salon();
        when(salonRepository.findById(salonId)).thenReturn(Optional.of(newSalon));
        newSalon.setCity(salon.getCity());
        newSalon.setAddress(salon.getAddress());
        when(salonRepository.save(newSalon)).thenReturn(newSalon);
        Optional<Salon> result = salonService.updateSalon(salonId, salon);
        assertEquals(Optional.of(newSalon), result);
    }
    @Test
    public void testAddNewSalon(){
        Salon salon = new Salon();
        when(salonRepository.save(salon)).thenReturn(salon);
        Optional<Salon> result = salonService.addNewSalon(salon);
        assertEquals(Optional.of(salon), result);
    }
    @Test
    public void testDeleteSalon(){
        Long salonId = 1L;
        salonService.deleteSalon(salonId);
        verify(salonRepository).deleteById(salonId);
    }

}
