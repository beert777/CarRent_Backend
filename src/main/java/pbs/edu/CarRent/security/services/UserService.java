package pbs.edu.CarRent.security.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pbs.edu.CarRent.dto.UserUpdateDTO;
import pbs.edu.CarRent.model.User;
import pbs.edu.CarRent.repository.UserRepository;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public abstract class UserService {
    private final UserRepository userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> getItemsList(){
        return userRepo.findAll();
    }
    public Optional<User> addUser(User user){
        user.setEmail(user.getEmail());
        user.setFirstName(user.getFirstName());
        user.setLastName(user.getLastName());
        user.setUsername(user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return  Optional.of(userRepo.save(user));

    }
    public Optional<User> getUserById(Long id){
        return userRepo.findById(id);
    }

    public abstract Optional<User> updateUser(Long id, UserUpdateDTO updatedUser);
}

