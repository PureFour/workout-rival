package com.ruczajsoftware.workoutrival.endpoints;

import com.ruczajsoftware.workoutrival.service.DatabaseService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(WorkoutRivalController.class)
class WorkoutRivalControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private DatabaseService databaseService;

    // write test cases here
}