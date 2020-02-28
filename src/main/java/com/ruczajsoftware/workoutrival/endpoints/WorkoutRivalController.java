package com.ruczajsoftware.workoutrival.endpoints;

import com.ruczajsoftware.workoutrival.exceptions.EntityConflictException;
import com.ruczajsoftware.workoutrival.exceptions.EntityNotFoundException;
import com.ruczajsoftware.workoutrival.model.Exercise;
import com.ruczajsoftware.workoutrival.model.Training;
import com.ruczajsoftware.workoutrival.service.DatabaseService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@EnableSwagger2
@RestController
@RequestMapping("workoutRival")
@AllArgsConstructor
public class WorkoutRivalController {

    private final DatabaseService databaseService;

    @ApiOperation(value = "Add training to user")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Training added!"),
		@ApiResponse(code = 404, message = "User not found!", response = EntityNotFoundException.class),
		@ApiResponse(code = 409, message = "Training exist!", response = EntityConflictException.class),
	})
	@PutMapping("/trainings/users")
	public void postTraining(@RequestBody Training training, @RequestParam String userLogin) throws EntityConflictException, EntityNotFoundException {
		databaseService.addTrainingToUser(training, userLogin);
	}

	@ApiOperation(value = "Get user trainings")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Operation successful!"),
		@ApiResponse(code = 404, message = "User not found!", response = EntityNotFoundException.class)
	})
	@GetMapping("/trainings")
	public ResponseEntity<List<Training>> getTrainings(@RequestParam String userLogin) throws EntityNotFoundException {
		return ResponseEntity.ok(databaseService.getUserTrainings(userLogin));
	}

	@ApiOperation(value = "Get training!")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Operation successful!"),
		@ApiResponse(code = 404, message = "Training not found!", response = EntityNotFoundException.class)
	})
	@GetMapping("/training")
	public ResponseEntity<Training> getTrainingByTrainingName(@RequestParam String trainingName) throws EntityNotFoundException {
		return ResponseEntity.ok(databaseService.getTrainingByTrainingName(trainingName));
	}

	@ApiOperation(value = "Delete training")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Operation successful!"),
		@ApiResponse(code = 404, message = "Training not found!", response = EntityNotFoundException.class)
	})
	@DeleteMapping("/trainings")
	public void deleteTrainingByTrainingName(@RequestParam String trainingName) throws EntityNotFoundException {
		databaseService.deleteTrainingByTrainingName(trainingName);
	}

	@ApiOperation(value = "Add exercise")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Exercise added!"),
		@ApiResponse(code = 409, message = "Exercise exist!")
	})
	@PostMapping("/exercises")
	public void postExercise(@RequestBody Exercise exercise) {
		databaseService.addExercise(exercise);
	}
}
