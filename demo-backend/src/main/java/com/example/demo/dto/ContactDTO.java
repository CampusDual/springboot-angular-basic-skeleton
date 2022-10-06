package com.example.demo.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.example.demo.utils.Constant;

public class ContactDTO {

	private Integer id;
	@NotEmpty(message = Constant.NAME_REQUIRED)
	private String name;
	
	@NotEmpty(message = Constant.SURNAME1_REQUIRED)
	private String surname1;
	
	@NotEmpty(message = Constant.SURNAME2_REQUIRED)
	private String surname2;
	
	@NotNull(message = Constant.PHONE_REQUIRED)
	private Integer phone;
	
	@Email(message= Constant.EMAIL_INVALID)
	@NotEmpty(message = Constant.EMAIL_REQUIRED)
	private String email;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname1() {
		return surname1;
	}

	public void setSurname1(String surname1) {
		this.surname1 = surname1;
	}

	public String getSurname2() {
		return surname2;
	}

	public void setSurname2(String surname2) {
		this.surname2 = surname2;
	}

	public Integer getPhone() {
		return phone;
	}

	public void setPhone(Integer phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
