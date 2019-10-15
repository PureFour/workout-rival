package com.ruczajsoftware.workoutrival.endpoints;

import com.ruczajsoftware.workoutrival.AuthorizationService;
import com.ruczajsoftware.workoutrival.exceptions.UnAuthorizedException;
import com.ruczajsoftware.workoutrival.exceptions.UserNotFoundException;
import com.ruczajsoftware.workoutrival.model.Exercise;
import com.ruczajsoftware.workoutrival.model.Training;
import com.ruczajsoftware.workoutrival.model.User;
import com.ruczajsoftware.workoutrival.service.DatabaseService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@RestController
@RequestMapping("workoutRival")
public class WorkoutRivalController {

	private final DatabaseService databaseService;
	private final AuthorizationService authorizationService;

	public WorkoutRivalController(DatabaseService databaseService, AuthorizationService authorizationService) {
		this.databaseService = databaseService;
		this.authorizationService = authorizationService;
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

	@ApiOperation(value = "Add exercise")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Exercise added!")
	})
	@PostMapping("/exercises")
	public void postTraining(@RequestBody Exercise exercise) {
		databaseService.addExercise(exercise);
	}

	@ApiOperation(value = "Add user")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "User added!")
	})
	@PostMapping("/users")
	public void postUser(@RequestBody User user) {
		databaseService.addUser(user);
	}

	@ApiOperation(value = "Get user")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Got user!")
	})

	@GetMapping("/users")
	public ResponseEntity<User> getUserByLogin(@RequestParam String login) {
		return ResponseEntity.ok(databaseService.getUserByLogin(login));
	}

	@GetMapping("/users/login")
	public ResponseEntity<Boolean> authorizeUser(@RequestParam String login, @RequestParam String password) throws UnAuthorizedException, UserNotFoundException {
		return ResponseEntity.ok(authorizationService.authorizeUser(login, password));
	}

}
