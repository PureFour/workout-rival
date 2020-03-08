package com.ruczajsoftware.workoutrival.endpoints;

import com.ruczajsoftware.workoutrival.model.User;
import com.ruczajsoftware.workoutrival.service.AuthorizationService;
import com.ruczajsoftware.workoutrival.service.DatabaseService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {
    private static final String BASE_URL = "/workoutRival/users";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private DatabaseService databaseService;

    @MockBean
    private AuthorizationService authorizationService;

    @Test
    public void getUserShouldReturnUser()
            throws Exception {

        User user = User.builder()
                .email("test@test.com")
                .login("test")
                .password("test")
                .build();

        given(databaseService.getUserByLogin("test")).willReturn(user);

        mvc.perform(get(BASE_URL)
                .param("login", user.getLogin())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value(user.getEmail()));

    }
}