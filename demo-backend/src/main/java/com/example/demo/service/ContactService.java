package com.example.demo.service;

import java.util.List;

import com.borjaglez.springify.repository.filter.impl.AnyPageFilter;
import com.example.demo.rest.model.CreateContactRequest;
import com.example.demo.rest.model.EditContactRequest;
import com.example.demo.rest.response.ContactResponse;
import com.example.demo.rest.response.DataSourceRESTResponse;
import com.example.demo.rest.response.RESTResponse;

public interface ContactService {

	/**
	 * Obtiene un usuario de BDD con el id indicado.
	 * 
	 * @param id el id del usuario de la BDD.
	 * @return el usuario cuyo id sea el pasado por parámetros.
	 */
	RESTResponse<ContactResponse> getContact(Integer id);

	/**
	 * Devuelve los usuarios que alguno de sus campos contenga la 'query'
	 * independientemente de las mayúsculas.
	 * 
	 * @param pageFilter filtro de la tabla
	 * @return usuarios que alguno de sus campos contenga la 'query'
	 *         independientemente de las mayúsculas.
	 * @since 0.0.5
	 */
	DataSourceRESTResponse<List<ContactResponse>> getContacts(AnyPageFilter pageFilter);

	/**
	 * Crea un nuevo usuario en la BDD.
	 * 
	 * @return el id del usuario creado.
	 * @since 0.0.5
	 */
	RESTResponse<Integer> createContact(CreateContactRequest createContactRequest);

	/**
	 * Modifica un usuario en la BDD.
	 * 
	 * @return el id del usuario modificado.
	 * @since 0.0.5
	 */
	RESTResponse<Integer> editContact(EditContactRequest editContactRequest);

	/**
	 * Elimina un usuario de la BDD.
	 * 
	 * @return el id del usuario eliminado.
	 * @since 0.0.5
	 */
	RESTResponse<Integer> deleteContact(Integer id);
}
