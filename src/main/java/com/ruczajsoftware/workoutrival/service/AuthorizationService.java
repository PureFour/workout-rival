package com.ruczajsoftware.workoutrival.service;

import com.ruczajsoftware.workoutrival.exceptions.EntityNotFoundException;
import com.ruczajsoftware.workoutrival.exceptions.UnauthorizedException;
import com.ruczajsoftware.workoutrival.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class AuthorizationService {

	private DatabaseService databaseService;

	public boolean authorizeUser(String login, String password) throws EntityNotFoundException, UnauthorizedException {

		User user = databaseService.getUserByLogin(login);
		if (user.getPassword().equals("")) throw new EntityNotFoundException("No user for this login in base");
		if (!user.getPassword().equals(password)) throw new UnauthorizedException("Bad password");
		return password.equals(user.getPassword());
	}
}