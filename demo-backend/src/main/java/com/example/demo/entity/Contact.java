package com.example.demo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.example.demo.utils.Constant;

@Entity
@Table(name = "contacts")
public class Contact implements Serializable{


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty(message = Constant.NAME_REQUIRED)
	@Column(nullable=false)
	private String name;
	
	@NotEmpty(message = Constant.SURNAME1_REQUIRED)
	@Column(nullable=false)
	private String surname1;
	
	@NotEmpty(message = Constant.SURNAME2_REQUIRED)
	@Column(nullable=false)
	private String surname2;
	
	@NotNull(message = Constant.PHONE_REQUIRED)
	@Column(nullable=false, unique = true)
	private Integer phone;
	
	@NotEmpty(message = Constant.EMAIL_REQUIRED)
	@Email(message= Constant.EMAIL_INVALID)
	@Column(nullable=false)
	private String email;

	public Contact() {
	}
	
	public Contact(String name, String surname1, String surname2, Integer phone, String email) {
		this.name = name;
		this.surname1 = surname1;
		this.surname2 = surname2;
		this.phone = phone;
		this.email = email;
	}

	public Contact(Integer id, String name, String surname1, String surname2, Integer phone, String email) {
		this(name, surname1, surname2, phone, email);
		this.id = id;
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
	private static final long serialVersionUID = 1L;
}