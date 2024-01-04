package pbs.edu.CarRent.service;


import pbs.edu.CarRent.dto.UserUpdateDTO;
import pbs.edu.CarRent.model.User;

import java.util.Optional;

public interface UserService {

    Optional<User> getUserById(Long id);

    Optional<User> updateUser(Long id, UserUpdateDTO user);
}
