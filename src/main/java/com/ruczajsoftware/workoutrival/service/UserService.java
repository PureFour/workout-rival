package com.ruczajsoftware.workoutrival.service;

import com.ruczajsoftware.workoutrival.model.database.PersonalData;
import com.ruczajsoftware.workoutrival.model.database.User;
import com.ruczajsoftware.workoutrival.model.exceptions.BadRequestException;
import com.ruczajsoftware.workoutrival.model.exceptions.EntityConflictException;
import com.ruczajsoftware.workoutrival.model.exceptions.EntityNotFoundException;
import com.ruczajsoftware.workoutrival.model.exceptions.ExceptionMessages;
import com.ruczajsoftware.workoutrival.model.web.CreateUserRequest;
import com.ruczajsoftware.workoutrival.model.web.UpdateUserPasswordRequest;
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
    private EmailService emailService;
    private PinService pinService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return mapUserToUserDetails(user.get());
        }
        throw new UsernameNotFoundException(ExceptionMessages.USER_NOT_FOUND.getMsg());
    }

    public boolean addUser(CreateUserRequest createUserRequest) throws EntityConflictException, BadRequestException, EntityNotFoundException {
        if (userRepository.findByUsername(createUserRequest.getUsername()).isPresent()) {
            throw new EntityConflictException(ExceptionMessages.USER_CONFLICT);
        }
        userRepository.save(mapCreateUserRequestToUser(createUserRequest));
        return true;
    }

    public User getUserByUsername(String username) throws EntityNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        return user.orElseThrow(() -> new EntityNotFoundException(ExceptionMessages.USER_NOT_FOUND));
    }

    public User getUserByEmail(String userEmail) throws EntityNotFoundException {
        Optional<User> user = userRepository.findByEmail(userEmail);
        return user.orElseThrow(() -> new EntityNotFoundException(ExceptionMessages.USER_NOT_FOUND));
    }

    public void deleteUserByUsername(String username) throws EntityNotFoundException {
        if (userRepository.findByUsername(username).isEmpty()) {
            throw new EntityNotFoundException(ExceptionMessages.USER_NOT_FOUND);
        }
        userRepository.deleteUserByUsername(username);
    }

    public void updateUserPassword(UpdateUserPasswordRequest updateUserPasswordRequest) throws EntityNotFoundException, EntityConflictException, BadRequestException {
        pinService.checkPasswordResetPin(updateUserPasswordRequest.getEmail(), updateUserPasswordRequest.getPin());

        User user = this.getUserByEmail(updateUserPasswordRequest.getEmail());
        if (user.getPassword().equals(updateUserPasswordRequest.getNewPassword())) {
            throw new EntityConflictException(ExceptionMessages.PASSWORD_CONFLICT);
        }
        user.setPassword(updateUserPasswordRequest.getNewPassword());
        userRepository.deleteUserByEmail(updateUserPasswordRequest.getEmail());
        userRepository.save(user);
    }

    public void updateUserEmail(String username, String email) throws EntityNotFoundException, EntityConflictException, BadRequestException {
        validateEmail(email);
        User user = this.getUserByUsername(username);
        if (user.getEmail().equals(email)) {
            throw new EntityConflictException(ExceptionMessages.EMAIL_CONFLICT);
        }
        user.setEmail(email);
        userRepository.deleteUserByUsername(username);
        userRepository.save(user);
    }

    public void resetPassword(String userEmail) throws BadRequestException, EntityNotFoundException {
        validateEmail(userEmail);
        final String pin = pinService.createAndSavePin(userEmail);
        final String username = this.getUserByEmail(userEmail).getUsername();
        emailService.sendPasswordResetPinToUser(username, userEmail, pin);
    }

    private String validateEmail(String email) throws BadRequestException, EntityNotFoundException {
        String regex = "^[\\w-_.+]*[\\w-_.]@([\\w]+\\.)+[\\w]+[\\w]$";
        if (!email.matches(regex)) {
            throw new BadRequestException(ExceptionMessages.EMAIL_INCORRECT);
        }

        if (userRepository.findByEmail(email).isEmpty()) {
            throw new EntityNotFoundException(ExceptionMessages.USER_NOT_FOUND);
        }

        return email;
    }

    private UserDetails mapUserToUserDetails(User user) {
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), new ArrayList<>());
    }

    private User mapCreateUserRequestToUser(CreateUserRequest createUserRequest) throws BadRequestException, EntityNotFoundException {
        return User.builder()
                .username(createUserRequest.getUsername())
                .password(createUserRequest.getPassword())
                .email(validateEmail(createUserRequest.getEmail()))
                .personalData(new PersonalData())
                .bodyMeasurements(new ArrayList<>())
                .definedTrainings(new ArrayList<>())
                .trainingPlans(new ArrayList<>())
                .build();
    }
}
