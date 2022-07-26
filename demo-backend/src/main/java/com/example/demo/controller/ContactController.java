package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.ContactDTO;
import com.example.demo.entity.Contact;
import com.example.demo.repository.ContactRepository;

@Controller
@RequestMapping(path = "/demo")
public class ContactController {
	@Autowired
	private ContactRepository contactRepository;

	@PostMapping(path = "/add")
	public @ResponseBody String addNewContact(@RequestBody ContactDTO contactDTO) {
		Contact contact = new Contact();
		contact.setName(contactDTO.getName());
		contact.setSurname1(contactDTO.getSurname1());
		contact.setSurname2(contactDTO.getSurname2());
		contact.setPhone(contactDTO.getPhone());
		contact.setEmail(contactDTO.getEmail());
		contactRepository.save(contact);
		return "Saved";
	}

	@GetMapping(path = "/all")
	public @ResponseBody Iterable<Contact> getAllContacts() {
		return contactRepository.findAll();
	}
}