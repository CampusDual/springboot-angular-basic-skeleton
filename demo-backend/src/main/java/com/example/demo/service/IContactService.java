package com.example.demo.service;

import java.util.List;

import com.borjaglez.springify.repository.filter.impl.AnyPageFilter;
import com.example.demo.dto.ContactDTO;
import com.example.demo.entity.Contact;
import com.example.demo.rest.response.DataSourceRESTResponse;

public interface IContactService {

	/**
	 * Obtiene un usuario de BDD con el id indicado.
	 * 
	 * @param id el id del usuario de la BDD.
	 * @return el usuario cuyo id sea el pasado por parámetros.
	 */
	ContactDTO getContact(Integer id);

	/**
	 * Devuelve los usuarios que alguno de sus campos contenga la 'query'
	 * independientemente de las mayúsculas.
	 * 
	 * @param pageFilter filtro de la tabla
	 * @return usuarios que alguno de sus campos contenga la 'query'
	 *         independientemente de las mayúsculas.
	 * @since 0.0.5
	 */
	DataSourceRESTResponse<List<ContactDTO>> getContacts(AnyPageFilter pageFilter);

	/**
	 * Crea un nuevo usuario en la BDD.
	 * 
	 * @return el id del usuario creado.
	 * @since 0.0.5
	 */
	ContactDTO createContact(ContactDTO createContactRequest);

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
	List<ContactDTO> findAll();
	
	/**
	 * Modifica un usuario en la BDD.
	 * 
	 * @return el id del usuario modificado.
	 * @since 0.0.5
	 */
	Integer editContact(ContactDTO editContactRequest);
	
}
