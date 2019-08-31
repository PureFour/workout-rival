package com.ruczajsoftware.workoutrival;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WorkoutRivalApplication.class)
class WorkoutRivalApplicationTest {
	@Test
	public void contextLoads() {
		Assertions.assertTrue(true, "It must be true!");
	}
}