package pbs.edu.CarRent.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import pbs.edu.CarRent.dto.UserUpdateDTO;
import pbs.edu.CarRent.model.User;
import pbs.edu.CarRent.repository.UserRepository;
import pbs.edu.CarRent.service.Impl.UserServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private UserServiceImpl userService;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetUserById(){
        Long userId = 1L;
        User user = new User();
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        Optional<User> result = userService.getUserById(userId);
        assertEquals(Optional.of(user), result);
    }
    @Test
    public void testUpdateUser(){
        Long userId = 1L;
        User user = new User();
        UserUpdateDTO updatedUser = new UserUpdateDTO("newName", "newLastName", "newEmail", "newUsername", "newPass");
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        user.setUsername(updatedUser.getUsername());
        user.setEmail(updatedUser.getEmail());
        user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        when(userRepository.save(user)).thenReturn(user);
        Optional<User> result = userService.updateUser(userId, updatedUser);
        assertEquals(Optional.of(user), result);
    }

}
