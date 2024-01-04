package pbs.edu.CarRent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pbs.edu.CarRent.model.Salon;


public interface SalonRepository extends JpaRepository<Salon, Long> {
}
