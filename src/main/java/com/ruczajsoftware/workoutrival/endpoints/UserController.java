package com.ruczajsoftware.workoutrival.endpoints;

import com.ruczajsoftware.workoutrival.exceptions.EntityConflictException;
import com.ruczajsoftware.workoutrival.exceptions.EntityNotFoundException;
import com.ruczajsoftware.workoutrival.exceptions.UnauthorizedException;
import com.ruczajsoftware.workoutrival.model.User;
import com.ruczajsoftware.workoutrival.service.AuthorizationService;
import com.ruczajsoftware.workoutrival.service.DatabaseService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "workoutRival/users", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class UserController {

    private final DatabaseService databaseService;
    private final AuthorizationService authorizationService;

    @ApiOperation(value = "Add user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User added!"),
            @ApiResponse(code = 409, message = "User exist!", response = EntityConflictException.class)
    })
    @PostMapping()
    public void postUser(@RequestBody User user) throws EntityConflictException {
        databaseService.addUser(user);
    }

    @ApiOperation(value = "Get user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Got user!"),
            @ApiResponse(code = 404, message = "User not found!", response = EntityNotFoundException.class)
    })
    @GetMapping()
    public ResponseEntity<User> getUserByLogin(@RequestParam String login) throws EntityNotFoundException {
        return ResponseEntity.ok(databaseService.getUserByLogin(login));
    }

    @ApiOperation(value = "Update user password")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Password updated!"),
            @ApiResponse(code = 404, message = "User not found!", response = EntityNotFoundException.class),
            @ApiResponse(code = 409, message = "Given password is the same!", response = EntityConflictException.class),
    })
    @PutMapping("/password")
    public void updateUserPassword(@RequestParam String login, @RequestParam String password) throws EntityNotFoundException, EntityConflictException {
        databaseService.updateUserPassword(login, password);
    }

    @ApiOperation(value = "Update user email")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Email updated!"),
            @ApiResponse(code = 404, message = "User not found!", response = EntityNotFoundException.class),
            @ApiResponse(code = 409, message = "Given email is the same!", response = EntityConflictException.class),
    })
    @PutMapping("/email")
    public void updateUserEmail(@RequestParam String login, @RequestParam(name="New email") String email) throws EntityNotFoundException, EntityConflictException {
        databaseService.updateUserEmail(login, email);
    }

    @ApiOperation(value = "Delete user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation successful!"),
            @ApiResponse(code = 404, message = "User not found!", response = EntityNotFoundException.class)
    })
    @DeleteMapping()
    public void deleteUser(@RequestParam String login) throws EntityNotFoundException {
        databaseService.deleteUserByLogin(login);
    }

    @ApiOperation(value = "Authorize user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation successful!"),
            @ApiResponse(code = 401, message = "Unauthorized!", response = UnauthorizedException.class),
            @ApiResponse(code = 404, message = "User not found!", response = EntityNotFoundException.class),
    })
    @GetMapping("/login")
    public ResponseEntity<Boolean> authorizeUser(@RequestParam String login, @RequestParam String password) throws UnauthorizedException, EntityNotFoundException {
        return ResponseEntity.ok(authorizationService.authorizeUser(login, password));
    }
}
