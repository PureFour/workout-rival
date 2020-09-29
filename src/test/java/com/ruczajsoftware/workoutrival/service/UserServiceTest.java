package com.ruczajsoftware.workoutrival.service;

import com.ruczajsoftware.workoutrival.model.database.PersonalDataBuilder;
import com.ruczajsoftware.workoutrival.model.database.Sex;
import com.ruczajsoftware.workoutrival.model.database.User;
import com.ruczajsoftware.workoutrival.model.database.UserBuilder;
import com.ruczajsoftware.workoutrival.model.exceptions.BadRequestException;
import com.ruczajsoftware.workoutrival.model.exceptions.EntityNotFoundException;
import com.ruczajsoftware.workoutrival.repositories.UserRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.mockito.BDDMockito.given;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @MockBean
    private UserRepository userRepository;
    @MockBean
    private EmailService emailService;
    @MockBean
    private PinService pinService;

    @Autowired
    private UserService userService;

    @Test
    void getUserBMI() throws BadRequestException, EntityNotFoundException {
        final User user1 = new UserBuilder()
                .username("test1")
                .password("")
                .email("")
                .definedTrainings(List.of())
                .trainingPlans(List.of())
                .bodyMeasurements(List.of())
                .personalData(new PersonalDataBuilder()
                        .height(181)
                        .weight(72.5f)
                        .birthday(new Date())
                        .sex(Sex.MALE)
                        .build()
                )
                .build();
        given(userRepository.findByUsername("test1")).willReturn(java.util.Optional.of(user1));
        Assert.assertEquals(22.1f, userService.getUserBMI("test1"), 0.1);
    }
}