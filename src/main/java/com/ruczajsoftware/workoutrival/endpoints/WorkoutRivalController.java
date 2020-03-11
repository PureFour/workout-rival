package com.ruczajsoftware.workoutrival.endpoints;

import com.ruczajsoftware.workoutrival.service.DatabaseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@RestController
@RequestMapping("workoutRival")
@AllArgsConstructor
public class WorkoutRivalController {

    private final DatabaseService databaseService;


}
