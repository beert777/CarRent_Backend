package pbs.edu.CarRent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pbs.edu.CarRent.model.Role;
import pbs.edu.CarRent.model.RoleName;

import java.util.Optional;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName name);
}
