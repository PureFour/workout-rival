package com.ruczajsoftware.workoutrival.endpoints;

import javax.validation.Valid;

import com.ruczajsoftware.workoutrival.model.Training;
import com.ruczajsoftware.workoutrival.service.DatabaseService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@RestController
@RequestMapping("workoutRival")
public class WorkoutRivalController {

	private final DatabaseService databaseService;

	public WorkoutRivalController(DatabaseService databaseService) {
		this.databaseService = databaseService;
	}

	@ApiOperation(value = "Sample endpoint :0")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Successfully shows message"),
		@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
		@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	@GetMapping()
	public ResponseEntity<String> sampleEndpoint() {
		return ResponseEntity.ok("Hello from WorkoutRival !");
	}

	@ApiOperation(value = "Add training")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Training added!")
	})
	@PostMapping("/trainings")
	public void postTraining(@RequestBody Training training) {
		databaseService.addTraining(training);
	}
}
