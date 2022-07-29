package com.example.demo.rest.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.borjaglez.springify.repository.filter.impl.AnyPageFilter;
import com.example.demo.exception.DemoException;
import com.example.demo.rest.model.CreateContactRequest;
import com.example.demo.rest.model.EditContactRequest;
import com.example.demo.rest.response.ContactResponse;
import com.example.demo.rest.response.RESTResponse;
import com.example.demo.service.ContactService;

@RestController
@RequestMapping(ContactsController.REQUEST_MAPPING)
public class ContactsController {
	public static final String REQUEST_MAPPING = "contacts";

	private static final Logger LOGGER = LoggerFactory.getLogger(ContactsController.class);

	@Autowired
	private ContactService contactService;

	/**
	 * Obtiene un contacto de BDD con el id indicado.
	 * 
	 * @param id el id del contacto de la BDD.
	 * @return el contacto cuyo id sea el pasado por parámetros.
	 */
	@GetMapping("getContact")
	@PreAuthorize("hasAnyAuthority('CONTACTS')")
	public RESTResponse<ContactResponse> getContact(@RequestParam(value = "id") Integer id) {
		LOGGER.info("getContact in progress...");
		try {
			return contactService.getContact(id);
		} catch (DemoException e) {
			LOGGER.error(e.getMessage());
			return new RESTResponse<>(e.getMessage());
		} finally {
			LOGGER.info("getContact is finished...");
		}
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
	public @ResponseBody RESTResponse<List<ContactResponse>> getContacts(@RequestBody AnyPageFilter pageFilter) {
		LOGGER.info("getContacts in progress...");
		try {
			return contactService.getContacts(pageFilter);
		} catch (DemoException e) {
			LOGGER.error(e.getMessage());
			return new RESTResponse<>(e.getMessage());
		} finally {
			LOGGER.info("getContacts is finished...");
		}
	}

	/**
	 * Llamada REST para crear un nuevo usuario en la BDD.
	 * 
	 * @return el id del usuario creado.
	 * @since 0.0.5
	 */
	@PostMapping(path = "/createContact", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyAuthority('CONTACTS')")
	public RESTResponse<Integer> createContact(@RequestBody CreateContactRequest createContactRequest) {
		LOGGER.info("createContact in progress...");
		try {
			return contactService.createContact(createContactRequest);
		} catch (DemoException e) {
			LOGGER.error(e.getMessage());
			return new RESTResponse<>(e.getMessage());
		} finally {
			LOGGER.info("createContact is finished...");
		}
	}

	/**
	 * Llamada REST para modificar un usuario en la BDD.
	 * 
	 * @return el id del usuario modificado.
	 * @since 0.0.5
	 */
	@PostMapping(path = "/editContact", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyAuthority('CONTACTS')")
	public RESTResponse<Integer> editContact(@RequestBody EditContactRequest editContactRequest) {
		LOGGER.info("editContact in progress...");
		try {
			return contactService.editContact(editContactRequest);
		} catch (DemoException e) {
			LOGGER.error(e.getMessage());
			return new RESTResponse<>(e.getMessage());
		} finally {
			LOGGER.info("editContact is finished...");
		}
	}

	/**
	 * Elimina un usuario de la BDD.
	 * 
	 * @return el id del usuario eliminado.
	 * @since 0.0.5
	 */
	@DeleteMapping("deleteContact")
	@PreAuthorize("hasAnyAuthority('CONTACTS')")
	public RESTResponse<Integer> deleteContact(@RequestParam(value = "id") Integer id) {
		LOGGER.info("deleteContact in progress...");
		try {
			return contactService.deleteContact(id);
		} catch (DemoException e) {
			LOGGER.error("({}) -> {}", id, e.getMessage());
			return new RESTResponse<>(e.getMessage());
		} finally {
			LOGGER.info("deleteContact is finished...");
		}
	}
}
