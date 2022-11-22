package com.example.demo.service;

import java.util.List;

import com.borjaglez.springify.repository.filter.impl.AnyPageFilter;
import com.example.demo.dto.ContactDTO;
import com.example.demo.entity.Contact;
import com.example.demo.rest.response.DataSourceRESTResponse;

public interface IContactService {

	/**
	 * Obtiene un contacto de BDD con el id indicado.
	 * 
	 * @param id el id del usuario de la BDD.
	 * @return el contacto cuyo id sea el pasado por parámetros.
	 */
	ContactDTO getContact(Integer id);

	/**
	 * Devuelve los contactos con paginación
	 * 
	 * @param pageFilter filtro de la tabla
	 * @return contactos paginados
	 * 
	 * @since 0.0.5
	 */
	DataSourceRESTResponse<List<ContactDTO>> getContacts(AnyPageFilter pageFilter);

	/**
	 * Crea un nuevo contacto en la BDD.
	 * 
	 * @return el id del contacto creado.
	 * @since 0.0.5
	 */
	ContactDTO createContact(ContactDTO createContactRequest);


	/**
	 * Elimina un contacto de la BDD.
	 * 
	 * @return el id del contacto eliminado.
	 * @since 0.0.5
	 */
	Integer deleteContact(Integer id);
	
	/**
	 * Devuelve todos los contactos que se encuentran en la tabla
	 * 
	 * @return todos los contactos que se encuentran en la BDD
	 * 
	 * @since 0.0.5
	 */
	List<ContactDTO> findAll();
	
	/**
	 * Modifica un contacto en la BDD.
	 * 
	 * @return el id del contacto modificado.
	 * @since 0.0.5
	 */
	Integer editContact(ContactDTO editContactRequest);
	
}
