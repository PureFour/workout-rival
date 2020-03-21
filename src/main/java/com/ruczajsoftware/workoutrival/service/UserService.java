package com.ruczajsoftware.workoutrival.service;

import com.ruczajsoftware.workoutrival.exceptions.EntityConflictException;
import com.ruczajsoftware.workoutrival.exceptions.EntityNotFoundException;
import com.ruczajsoftware.workoutrival.model.database.PersonalData;
import com.ruczajsoftware.workoutrival.model.web.CreateUserRequest;
import com.ruczajsoftware.workoutrival.model.database.User;
import com.ruczajsoftware.workoutrival.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return mapUserToUserDetails(user.get());
        }
        throw new UsernameNotFoundException("User not found in database!");
    }

    public boolean addUser(CreateUserRequest createUserRequest) throws EntityConflictException {
        if (userRepository.findByUsername(createUserRequest.getUsername()).isPresent()) {
            throw new EntityConflictException("User exists in database!");
        }
        userRepository.save(mapCreateUserRequestToUser(createUserRequest));
        return true;
    }

    public User getUserByUsername(String username) throws EntityNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        return user.orElseThrow(() -> new EntityNotFoundException("User not found in database!"));
    }

    public void deleteUserByUsername(String username) throws EntityNotFoundException {
        if (userRepository.findByUsername(username).isEmpty()) {
            throw new EntityNotFoundException("User not found!");
        }
        userRepository.deleteUserByUsername(username);
    }

    public void updateUserPassword(String username, String password) throws EntityNotFoundException, EntityConflictException {
        User user = this.getUserByUsername(username);
        if (user.getPassword().equals(password)) {
            throw new EntityConflictException("Password must be different!");
        }
        user.setPassword(password);
        userRepository.deleteUserByUsername(username);
        userRepository.save(user);
    }

    public void updateUserEmail(String username, String email) throws EntityNotFoundException, EntityConflictException {
        User user = this.getUserByUsername(username);
        if (user.getEmail().equals(email)) {
            throw new EntityConflictException("Email must be different!");
        }
        user.setEmail(email);
        userRepository.deleteUserByUsername(username);
        userRepository.save(user);
    }

    private UserDetails mapUserToUserDetails(User user) {
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), new ArrayList<>());
    }

    private User mapCreateUserRequestToUser(CreateUserRequest createUserRequest) {
        return User.builder()
                .username(createUserRequest.getUsername())
                .password(createUserRequest.getPassword())
                .email(createUserRequest.getEmail())
                .personalData(new PersonalData())
                .bodyMeasurements(new ArrayList<>())
                .definedTrainings(new ArrayList<>())
                .trainingPlans(new ArrayList<>())
                .build();
    }
}
