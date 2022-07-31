package com.example.demo.rest.response;

import com.example.demo.entity.Contact;

public class ContactResponse {
	private Integer id;
	private String name;
	private String surname1;
	private String surname2;
	private Integer phone;
	private String email;

	public ContactResponse(Integer id, String name, String surname1, String surname2, Integer phone, String email) {
		this.id = id;
		this.name = name;
		this.surname1 = surname1;
		this.surname2 = surname2;
		this.phone = phone;
		this.email = email;
	}

	public ContactResponse(Contact contact) {
		this.id = contact.getId();
		this.name = contact.getName();
		this.surname1 = contact.getSurname1();
		this.surname2 = contact.getSurname2();
		this.phone = contact.getPhone();
		this.email = contact.getEmail();
	}

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
