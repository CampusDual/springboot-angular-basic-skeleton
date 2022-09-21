package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.entity.Contact;

public interface IContactService {

	/**
	 * Obtiene un usuario de BDD con el id indicado.
	 * 
	 * @param id el id del usuario de la BDD.
	 * @return el usuario cuyo id sea el pasado por parámetros.
	 */
	Contact getContact(Integer id);

	/**
	 * Devuelve los usuarios que alguno de sus campos contenga la 'query'
	 * independientemente de las mayúsculas.
	 * 
	 * @param pageFilter filtro de la tabla
	 * @return usuarios que alguno de sus campos contenga la 'query'
	 *         independientemente de las mayúsculas.
	 * @since 0.0.5
	 */
	Page<Contact> getContacts(Pageable pageFilter);

	/**
	 * Crea un nuevo usuario en la BDD.
	 * 
	 * @return el id del usuario creado.
	 * @since 0.0.5
	 */
	Contact createContact(Contact createContactRequest);

	/**
	 * Elimina un usuario de la BDD.
	 * 
	 * @return el id del usuario eliminado.
	 * @since 0.0.5
	 */
	Integer deleteContact(Integer id);
	
	/**
	 * Devuelve todos los contactos que se encuentran en la tabla
	 * 
	 * @return usuarios que alguno de sus campos contenga la 'query'
	 *         independientemente de las mayúsculas.
	 * @since 0.0.5
	 */
	List<Contact> findAll();
}