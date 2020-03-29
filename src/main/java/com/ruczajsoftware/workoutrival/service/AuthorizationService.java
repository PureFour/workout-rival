package com.ruczajsoftware.workoutrival.service;

import com.ruczajsoftware.workoutrival.model.exceptions.EntityNotFoundException;
import com.ruczajsoftware.workoutrival.model.exceptions.ExceptionMessages;
import com.ruczajsoftware.workoutrival.model.exceptions.UnauthorizedException;
import com.ruczajsoftware.workoutrival.model.database.User;
import com.ruczajsoftware.workoutrival.model.authentication.AuthenticationRequest;
import com.ruczajsoftware.workoutrival.model.authentication.AuthenticationResponse;
import com.ruczajsoftware.workoutrival.service.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class AuthorizationService {

	private AuthenticationManager authenticationManager;
	private JwtUtil jwtUtil;
	private UserService userService;

	public boolean authorizeUser(String login, String password) throws EntityNotFoundException, UnauthorizedException {

		User user = userService.getUserByUsername(login);
		if (user.getPassword().equals("")) throw new EntityNotFoundException(ExceptionMessages.USER_NOT_FOUND);
		if (!user.getPassword().equals(password)) throw new UnauthorizedException(ExceptionMessages.INCORRECT_CREDENTIALS);
		return password.equals(user.getPassword());
	}


	public AuthenticationResponse authenticateUser(AuthenticationRequest authRequest) throws UnauthorizedException, EntityNotFoundException {

		final UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				authRequest.getUsername(), authRequest.getPassword());

		try {
			authenticationManager.authenticate(authenticationToken);
		} catch (BadCredentialsException e) {
			throw new UnauthorizedException(ExceptionMessages.INCORRECT_CREDENTIALS);
		}

		final User user = userService.getUserByUsername(authRequest.getUsername());

		final String jwtToken = "Bearer " + jwtUtil.generateToken(user);

		return AuthenticationResponse.builder().token(jwtToken).build();
	}
}