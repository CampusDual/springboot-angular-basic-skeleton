package com.example.demo.rest.model;

@SuppressWarnings("serial")
public class EditContactRequest extends CreateContactRequest {

	private Integer id;
	
	public EditContactRequest() {
		super();
	}
	
	public EditContactRequest(Integer id, String name, String surname1, String surname2, Integer phone, String email) {
		super(name, surname1, surname2, phone, email);
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
}
