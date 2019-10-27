package com.ruczajsoftware.workoutrival.service;

import com.ruczajsoftware.workoutrival.exceptions.EntityNotFoundException;
import com.ruczajsoftware.workoutrival.exceptions.UnAuthorizedException;
import com.ruczajsoftware.workoutrival.model.User;
import org.springframework.stereotype.Service;


@Service
public class AuthorizationService {

	private DatabaseService databaseService;

	public AuthorizationService(DatabaseService databaseService) {
		this.databaseService = databaseService;
	}

	public boolean authorizeUser(String login, String password) throws EntityNotFoundException, UnAuthorizedException {

		User user = databaseService.getUserByLogin(login);
		if(user.getPassword().equals("")) throw new EntityNotFoundException("No user for this login in base");
		if(!user.getPassword().equals(password)) throw new UnAuthorizedException("Bad password");
		return password.equals(user.getPassword());
	}
}