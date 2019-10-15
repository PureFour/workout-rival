package com.ruczajsoftware.workoutrival;

import com.ruczajsoftware.workoutrival.exceptions.UnAuthorizedException;
import com.ruczajsoftware.workoutrival.exceptions.UserNotFoundException;
import com.ruczajsoftware.workoutrival.model.User;
import com.ruczajsoftware.workoutrival.service.DatabaseService;
import org.springframework.stereotype.Service;


@Service
public class AuthorizationService {

	private DatabaseService databaseService;

	public AuthorizationService(DatabaseService databaseService) {
		this.databaseService = databaseService;
	}

	public boolean authorizeUser(String login, String password) throws UserNotFoundException, UnAuthorizedException {

		User user = databaseService.getUserByLogin(login);
		if(user.getPassword().equals("")) throw new UserNotFoundException("No user for this login in base");
		if(!user.getPassword().equals(password)) throw new UnAuthorizedException("Bad password");
		return password.equals(user.getPassword());
	}
}