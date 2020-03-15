package com.ruczajsoftware.workoutrival.service;

import com.ruczajsoftware.workoutrival.exceptions.EntityNotFoundException;
import com.ruczajsoftware.workoutrival.exceptions.UnauthorizedException;
import com.ruczajsoftware.workoutrival.model.database.User;
import com.ruczajsoftware.workoutrival.model.authentication.AuthenticationRequest;
import com.ruczajsoftware.workoutrival.model.authentication.AuthenticationResponse;
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

	public AuthenticationResponse authenticateUser(AuthenticationRequest authRequest) throws UnauthorizedException, EntityNotFoundException {

		final UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				authRequest.getUserLogin(), authRequest.getUserPassword());

		try {
			authenticationManager.authenticate(authenticationToken);
		} catch (BadCredentialsException e) {
			throw new UnauthorizedException("Incorrect username or password!");
		}

		final User user = userService.getUserByUsername(authRequest.getUserLogin());

		final String jwtToken = "Bearer " + jwtUtil.generateToken(user);

		return AuthenticationResponse.builder().token(jwtToken).build();
	}
}