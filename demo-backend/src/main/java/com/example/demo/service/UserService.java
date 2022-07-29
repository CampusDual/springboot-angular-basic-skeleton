package com.example.demo.service;

import com.example.demo.rest.response.RESTResponse;

public interface UserService {

	RESTResponse<Boolean> canLogin(String user);
}
