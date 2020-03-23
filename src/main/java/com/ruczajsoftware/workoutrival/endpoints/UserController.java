package com.ruczajsoftware.workoutrival.endpoints;

import com.ruczajsoftware.workoutrival.exceptions.EntityConflictException;
import com.ruczajsoftware.workoutrival.exceptions.EntityNotFoundException;
import com.ruczajsoftware.workoutrival.exceptions.UnauthorizedException;
import com.ruczajsoftware.workoutrival.model.authentication.AuthenticationRequest;
import com.ruczajsoftware.workoutrival.model.authentication.AuthenticationResponse;
import com.ruczajsoftware.workoutrival.model.database.User;
import com.ruczajsoftware.workoutrival.model.web.CreateUserRequest;
import com.ruczajsoftware.workoutrival.service.AuthorizationService;
import com.ruczajsoftware.workoutrival.service.UserService;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@EnableSwagger2
@RequestMapping(value = "workoutRival/users", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthorizationService authorizationService;

    @ApiOperation(value = "Add user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User added!"),
            @ApiResponse(code = 409, message = "User exist!", response = EntityConflictException.class)
    })
    @PostMapping("/signUp")
    public ResponseEntity<Boolean> postUser(@RequestBody CreateUserRequest createUserRequest) throws EntityConflictException {
        return ResponseEntity.ok(userService.addUser(createUserRequest));
    }

    @ApiOperation(value = "Get user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Got user!"),
            @ApiResponse(code = 404, message = "User not found!", response = EntityNotFoundException.class)
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization token (starts with 'Bearer')", dataType = "string", paramType = "header") })
    @GetMapping()
    public ResponseEntity<User> getUserByUsername(@RequestParam String username) throws EntityNotFoundException {
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }

    @ApiOperation(value = "Update user password")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Password updated!"),
            @ApiResponse(code = 404, message = "User not found!", response = EntityNotFoundException.class),
            @ApiResponse(code = 409, message = "Given password is the same!", response = EntityConflictException.class),
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization token (starts with 'Bearer')", dataType = "string", paramType = "header") })
    @PutMapping("/password")
    public void updateUserPassword(@RequestParam String username, @RequestParam String password) throws EntityNotFoundException, EntityConflictException {
        userService.updateUserPassword(username, password);
    }

    @ApiOperation(value = "Update user email")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Email updated!"),
            @ApiResponse(code = 404, message = "User not found!", response = EntityNotFoundException.class),
            @ApiResponse(code = 409, message = "Given email is the same!", response = EntityConflictException.class),
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization token (starts with 'Bearer')", dataType = "string", paramType = "header") })
    @PutMapping("/email")
    public void updateUserEmail(@RequestParam String username, @RequestParam(name="New email") String email) throws EntityNotFoundException, EntityConflictException {
        userService.updateUserEmail(username, email);
    }

    @ApiOperation(value = "Delete user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation successful!"),
            @ApiResponse(code = 404, message = "User not found!", response = EntityNotFoundException.class)
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization token (starts with 'Bearer')", dataType = "string", paramType = "header") })
    @DeleteMapping()
    public void deleteUser(@RequestParam String username) throws EntityNotFoundException {
        userService.deleteUserByUsername(username);
    }

    @ApiOperation(value = "Authenticate user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation successful!"),
            @ApiResponse(code = 401, message = "Unauthorized!", response = UnauthorizedException.class),
            @ApiResponse(code = 404, message = "User not found!", response = EntityNotFoundException.class),
    })
    @PostMapping("/signIn")
    public ResponseEntity<AuthenticationResponse> authenticateUser(@RequestBody AuthenticationRequest authRequest) throws UnauthorizedException, EntityNotFoundException {
        return ResponseEntity.ok(authorizationService.authenticateUser(authRequest));
    }

    @ApiOperation(value = "Authenticate user (old method)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation successful!"),
            @ApiResponse(code = 401, message = "Unauthorized!", response = UnauthorizedException.class),
            @ApiResponse(code = 404, message = "User not found!", response = EntityNotFoundException.class),
    })
    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody AuthenticationRequest authRequest) throws UnauthorizedException, EntityNotFoundException {
        return ResponseEntity.ok(authorizationService.authorizeUser(authRequest.getUsername(), authRequest.getPassword()));
    }
}
