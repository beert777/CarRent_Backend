package pbs.edu.CarRent.service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pbs.edu.CarRent.dto.UserUpdateDTO;
import pbs.edu.CarRent.model.User;
import pbs.edu.CarRent.repository.UserRepository;
import pbs.edu.CarRent.service.UserService;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> updateUser(Long id, UserUpdateDTO updatedUser) {
        User user = userRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        if (updatedUser.getUsername() != null) {
            user.setUsername(updatedUser.getUsername());
        }
        if (updatedUser.getEmail() != null) {
            user.setEmail(updatedUser.getEmail());
        }
        if (updatedUser.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        }
        return Optional.of(userRepository.save(user));
    }
}
