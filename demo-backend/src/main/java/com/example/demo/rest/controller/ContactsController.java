package com.example.demo.rest.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.borjaglez.springify.repository.filter.impl.AnyPageFilter;
import com.example.demo.entity.Contact;
import com.example.demo.exception.DemoException;
import com.example.demo.rest.response.DataSourceRESTResponse;
import com.example.demo.service.IContactService;
import com.example.demo.utils.Constant;

@CrossOrigin(origins = {"http://localhost:4201"})
@RestController
@RequestMapping(ContactsController.REQUEST_MAPPING)
public class ContactsController {
	public static final String REQUEST_MAPPING = "contacts";
	public static final String MESSAGE = "message";
	public static final String ERROR = "errors";
	private static final Logger LOGGER = LoggerFactory.getLogger(ContactsController.class);

	@Autowired
	private IContactService contactService;

	/**
	 * Obtiene un contacto de BDD con el id indicado.
	 * 
	 * @param id el id del contacto de la BDD.
	 * @return el contacto cuyo id sea el pasado por parámetros.
	 */
	@GetMapping("/getContact/{id}")
	@PreAuthorize("hasAnyAuthority('CONTACTS')")
	public ResponseEntity<?> getContact(@PathVariable Integer id) {
		LOGGER.info("getContact in progress...");
		Contact contact = null;
		Map<String, Object> response = new HashMap<>();
		ResponseEntity<?>re = null;
		try {
			contact = contactService.getContact(id);
			if(contact==null) {
				response.put(MESSAGE, Constant.CONTACT_NOT_EXISTS);
				re = new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}else {
				re = new ResponseEntity<Contact>(contact, HttpStatus.OK);
			}
		} catch (DataAccessException e) {
			LOGGER.error(e.getMessage());
			response.put(MESSAGE, Constant.DATABASE_QUERY_ERROR);
			response.put(ERROR, e.getMessage());
			re=  new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		} 
		LOGGER.info("getContact is finished...");
		return re;
	}

	/**
	 * Llamada REST para obtener usuarios que alguno de sus campos contenga la
	 * 'query' independientemente de las mayúsculas.
	 * 
	 * @return usuarios que alguno de sus campos contenga la 'query'
	 *         independientemente de las mayúsculas.
	 * @since 0.0.5
	 */
	@PostMapping(path = "/getContacts", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyAuthority('CONTACTS')")
	public @ResponseBody DataSourceRESTResponse<List<Contact>> getContacts(@RequestBody AnyPageFilter pageFilter) {
		LOGGER.info("getContacts in progress...");
		DataSourceRESTResponse<List<Contact>> dres = new DataSourceRESTResponse<>();
		try {
			dres = contactService.getContacts(pageFilter);
		} catch (DemoException e) {
			LOGGER.error(e.getMessage());
			dres.setResponseMessage(e.getMessage());
		} 
		LOGGER.info("getContacts is finished...");
		return dres;
	}
	
	/**
	 * Devuelve todos los contactos que se encuentran en la tabla
	 * 
	 * @return usuarios que alguno de sus campos contenga la 'query'
	 *         independientemente de las mayúsculas.
	 * @since 0.0.5
	 */
	@GetMapping(path = "/getContacts")
	@PreAuthorize("hasAnyAuthority('CONTACTS')")
	public @ResponseBody List<Contact> findAll() {
		LOGGER.info("findAll in progress...");
		return contactService.findAll();
	}

	/**
	 * Llamada REST para crear un nuevo usuario en la BDD.
	 * 
	 * @return el id del usuario creado.
	 * @since 0.0.5
	 */
	@PostMapping(path = "/createContact")
	@PreAuthorize("hasAnyAuthority('CONTACTS')")
	public ResponseEntity<?> createContact(@Valid @RequestBody Contact createContactRequest, BindingResult result) {
		LOGGER.info("createContact in progress...");
		Contact contactNew = null;
		Map<String, Object> response = new HashMap<>();
		HttpStatus status = HttpStatus.CREATED;
		String message = Constant.CONTACT_CREATE_SUCCESS;
		if(!result.hasErrors()) {
			try {
				contactNew = contactService.createContact(createContactRequest);	
			} catch (DataAccessException e) {
				message = Constant.DATABASE_QUERY_ERROR;
				response.put(ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				status= HttpStatus.INTERNAL_SERVER_ERROR;
			}
			response.put("contacto", contactNew);
		}else {
			List<String> errors = new ArrayList<>();
			for(FieldError error : result.getFieldErrors()) {
				errors.add(error.getDefaultMessage());
			}
			message = Constant.CONTACT_NOT_CREATED;
			response.put(ERROR, errors);
			status = HttpStatus.BAD_REQUEST;
		}
		
		LOGGER.info("createContact is finished...");
		response.put(MESSAGE, message);
		
		return new ResponseEntity<Map<String, Object>>(response, status);
	}

	/**
	 * Llamada REST para modificar un usuario en la BDD.
	 * 
	 * @return el id del usuario modificado.
	 * @since 0.0.5
	 */
	@PutMapping(path = "/editContact/{id}")
	@PreAuthorize("hasAnyAuthority('CONTACTS')")
	public ResponseEntity<?> editContact(@Valid @RequestBody Contact editContactRequest, BindingResult result, @PathVariable Integer id) {
		LOGGER.info("editContact in progress...");
		Contact current = contactService.getContact(id);
		Contact contactUpdate = null;
		Map<String, Object> response = new HashMap<>();
		HttpStatus status = HttpStatus.CREATED;
		String message = Constant.CONTACT_EDIT_SUCCESS;
		if(current !=null) {	
			if(!result.hasErrors()) {
				try {
					current.setEmail(editContactRequest.getEmail());
					current.setName(editContactRequest.getName());
					current.setPhone(editContactRequest.getPhone());
					current.setSurname1(editContactRequest.getSurname1());
					current.setSurname2(editContactRequest.getSurname2());
					contactUpdate = contactService.createContact(current);
					response.put("contact", contactUpdate);
				}catch (DataAccessException e) {
					message= Constant.DATABASE_QUERY_ERROR;
					response.put(ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
					status= HttpStatus.BAD_REQUEST;
				}
				
			}else {
				List<String> errors = new ArrayList<>();
				for(FieldError error : result.getFieldErrors()) {
					errors.add(error.getDefaultMessage());
				}
				message = Constant.CONTACT_NOT_EDIT;
				response.put(ERROR, errors);
				status = HttpStatus.BAD_REQUEST;
			}
			
		}else {
			status = HttpStatus.NOT_FOUND;
			message = Constant.CONTACT_NOT_EXISTS;
		}
		
		response.put(MESSAGE, message);
		response.put("contacto", contactUpdate);
		LOGGER.info("editContact is finished...");
		return new ResponseEntity<Map<String, Object>>(response, status);
	
	}

	/**
	 * Elimina un usuario de la BDD.
	 * 
	 * @return el id del usuario eliminado.
	 * @since 0.0.5
	 */
	@DeleteMapping("/deleteContact/{id}")
	@PreAuthorize("hasAnyAuthority('CONTACTS')")
	public ResponseEntity<?> deleteContact(@PathVariable Integer id) {
		LOGGER.info("deleteContact in progress...");
		Map<String, Object> response = new HashMap<>();
		HttpStatus status = HttpStatus.OK;
		String message = Constant.CONTACT_DELETE_SUCCESS;
		try {
			contactService.deleteContact(id);
		} catch (DataAccessException e) {
			response.put(MESSAGE, Constant.DATABASE_QUERY_ERROR);
			response.put(ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			message = Constant.CONTACT_NOT_DELETE;
		} 
		response.put(MESSAGE, message);
		LOGGER.info("deleteContact is finished...");
		return new ResponseEntity<Map<String, Object>>(response,status);
	}
}
