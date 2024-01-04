package pbs.edu.CarRent.controller;

import org.springframework.http.ResponseEntity;
import pbs.edu.CarRent.dto.UserUpdateDTO;
import pbs.edu.CarRent.model.User;


public interface UserController {
    ResponseEntity<User> getUserById(Long id);

    ResponseEntity<User> updateUser(Long id, UserUpdateDTO user);

}
