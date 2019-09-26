package com.microfocus.ring2parkms.web.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Client controller, fetches Users info from the microservice via
 * {@link WebUsersService}.
 * 
 * @author Kevin A. Lee
 */
@Controller
public class WebUsersController {

	@Autowired
	protected WebUsersService usersService;

	protected Logger logger = Logger.getLogger(WebUsersController.class.getName());

	public WebUsersController(WebUsersService usersService) {
		this.usersService = usersService;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setAllowedFields("searchText");
	}

	@RequestMapping("/users")
	public String allUsers(Model model) {
		logger.info("users-service allUsers() invoked: ");
		List<User> users = usersService.all();
		logger.info("users-service allUsers() found: " + users);
		if (users != null)
			model.addAttribute("users", users);
		return "users";
	}

	@RequestMapping("/user/{id}")
	public String byId(Model model, @PathVariable("id") Long id) {
		logger.info("users-service byId() invoked: " + id);
		User user = usersService.byId(id);
		logger.info("users-service byId() found: " + user);
		model.addAttribute("user", user);
		return "user";
	}

	@RequestMapping("/users/username/{name}")
	public String userSearch(Model model, @PathVariable("name") String name) {
		logger.info("users-service byUsername() invoked: " + name);
		List<User> users = usersService.byUsername(name);
		logger.info("users-service byUsername() found: " + users);
		model.addAttribute("search", name);
		if (users != null)
			model.addAttribute("users", users);
		return "users";
	}

	@RequestMapping(value = "/users/search", method = RequestMethod.GET)
	public String searchForm(Model model) {
		model.addAttribute("searchCriteria", new UserSearchCriteria());
		return "userSearch";
	}

	@RequestMapping(value = "/users/dosearch")
	public String doSearch(Model model, UserSearchCriteria criteria,
			BindingResult result) {
		logger.info("users-service search() invoked: " + criteria);
		criteria.validate(result);
		if (result.hasErrors())
			return "userSearch";
		String searchText = criteria.getSearchText();
		if (StringUtils.hasText(searchText)) {
			return userSearch(model, searchText);
		} else {
			return "userSearch";
		}
	}
}
