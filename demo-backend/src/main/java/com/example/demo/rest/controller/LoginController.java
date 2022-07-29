package com.example.demo.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.DemoException;
import com.example.demo.rest.response.RESTResponse;
import com.example.demo.service.UserService;

@RestController
@CrossOrigin
@RequestMapping(LoginController.REQUEST_MAPPING)
public class LoginController {

	public static final String REQUEST_MAPPING = "login";

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private UserService userService;

	@GetMapping("canLogin")
	public RESTResponse<Boolean> canLogin(@RequestParam(value = "user") String user) {
		LOGGER.info("canLogin in progress...");
		try {
			return userService.canLogin(user);
		} catch (DemoException e) {
			LOGGER.error(e.getMessage());
			return new RESTResponse<>(e.getMessage());
		} finally {
			LOGGER.info("canLogin is finished...");
		}
	}
}
