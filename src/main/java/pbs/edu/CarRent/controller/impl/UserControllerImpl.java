package pbs.edu.CarRent.controller.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pbs.edu.CarRent.controller.UserController;
import pbs.edu.CarRent.dto.UserUpdateDTO;
import pbs.edu.CarRent.model.User;
import pbs.edu.CarRent.service.UserService;


@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
@Slf4j
public class UserControllerImpl implements UserController {

    private UserService userService;

    @Override
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.of(userService.getUserById(id));
    }

    @Override
    @PatchMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody UserUpdateDTO user) {
        return ResponseEntity.of(userService.updateUser(id, user));
    }
}

