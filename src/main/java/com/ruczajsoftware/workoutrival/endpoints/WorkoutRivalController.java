package com.ruczajsoftware.workoutrival.endpoints;

import java.util.List;

import com.ruczajsoftware.workoutrival.exceptions.EntityConflictException;
import com.ruczajsoftware.workoutrival.exceptions.EntityNotFoundException;
import com.ruczajsoftware.workoutrival.exceptions.UnAuthorizedException;
import com.ruczajsoftware.workoutrival.model.Exercise;
import com.ruczajsoftware.workoutrival.model.Training;
import com.ruczajsoftware.workoutrival.model.User;
import com.ruczajsoftware.workoutrival.service.AuthorizationService;
import com.ruczajsoftware.workoutrival.service.DatabaseService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
		@ApiResponse(code = 200, message = "Successfully shows message")
	})
	@GetMapping()
	public ResponseEntity<String> sampleEndpoint() {
		return ResponseEntity.ok("Hello from WorkoutRival !");
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// TRAININGS

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
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////// EXERCISES

	@ApiOperation(value = "Add exercise")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Exercise added!"),
		@ApiResponse(code = 409, message = "Exercise exist!")
	})
	@PostMapping("/exercises")
	public void postExercise(@RequestBody Exercise exercise) {
		databaseService.addExercise(exercise);
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// USERS
	@ApiOperation(value = "Add user")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "User added!"),
		@ApiResponse(code = 409, message = "User exist!", response = EntityConflictException.class)
	})
	@PostMapping("/users")
	public void postUser(@RequestBody User user) throws EntityConflictException {
		databaseService.addUser(user);
	}

	@ApiOperation(value = "Get user")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Got user!"),
		@ApiResponse(code = 404, message = "User not found!", response = EntityNotFoundException.class)
	})
	@GetMapping("/users")
	public ResponseEntity<User> getUserByLogin(@RequestParam String login) throws EntityNotFoundException {
		return ResponseEntity.ok(databaseService.getUserByLogin(login));
	}

	@ApiOperation(value = "Update user password")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Password updated!"),
		@ApiResponse(code = 404, message = "User not found!", response = EntityNotFoundException.class),
		@ApiResponse(code = 409, message = "Given password is the same!", response = EntityConflictException.class),
	})
	@PutMapping("/users/password")
	public void updateUserPassword(@RequestParam String login, @RequestParam String password) throws EntityNotFoundException, EntityConflictException {
		databaseService.updateUserPassword(login, password);
	}

	@ApiOperation(value = "Update user email")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Email updated!"),
		@ApiResponse(code = 404, message = "User not found!", response = EntityNotFoundException.class),
		@ApiResponse(code = 409, message = "Given email is the same!", response = EntityConflictException.class),
	})
	@PutMapping("/users/email")
	public void updateUserEmail(@RequestParam String login, @RequestParam String email) throws EntityNotFoundException, EntityConflictException {
		databaseService.updateUserEmail(login, email);
	}

	@ApiOperation(value = "Delete user")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Operation successful!"),
		@ApiResponse(code = 404, message = "User not found!", response = EntityNotFoundException.class)
	})
	@DeleteMapping("/users")
	public void deleteUser(@RequestParam String login) throws EntityNotFoundException {
		databaseService.deleteUserByLogin(login);
	}

	@ApiOperation(value = "Authorize user")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Operation successful!"),
		@ApiResponse(code = 401, message = "Unauthorized!", response = UnAuthorizedException.class),
		@ApiResponse(code = 404, message = "User not found!", response = EntityNotFoundException.class),
	})
	@GetMapping("/users/login")
	public ResponseEntity<Boolean> authorizeUser(@RequestParam String login, @RequestParam String password) throws UnAuthorizedException, EntityNotFoundException {
		return ResponseEntity.ok(authorizationService.authorizeUser(login, password));
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
