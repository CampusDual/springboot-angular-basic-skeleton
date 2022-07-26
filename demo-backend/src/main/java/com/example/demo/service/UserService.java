package com.example.demo.service;

import com.example.demo.response.RESTResponse;

public interface UserService {

	RESTResponse<Boolean> canLogin(String user);
}
