package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.borjaglez.springify.repository.filter.impl.AnyPageFilter;
import com.borjaglez.springify.repository.specification.SpecificationBuilder;
import com.example.demo.entity.Contact;
import com.example.demo.repository.ContactRepository;
import com.example.demo.rest.response.DataSourceRESTResponse;

@Service
public class ContactServiceImpl extends AbstractDemoService implements IContactService {

	/**
	 * Especificación JPA para {@link Contact}.
	 */
	@Autowired
	private ContactRepository contactRepository;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Contact getContact(Integer id) {
		return contactRepository.findById(id).orElse(null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(readOnly = true)
	public DataSourceRESTResponse<List<Contact>> getContacts(AnyPageFilter pageFilter) {
		checkInputParams(pageFilter);
		Page<Contact> contacts = SpecificationBuilder.selectDistinctFrom(contactRepository).where(pageFilter)
				.findAll(pageFilter); 
		DataSourceRESTResponse<List<Contact>> datares = new DataSourceRESTResponse<>();
		datares.setTotalElements((int) contacts.getTotalElements());
		datares.setData(contacts.getContent());
		return datares;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public Contact createContact(Contact createContactRequest) {
		
		return contactRepository.save(createContactRequest);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public Integer deleteContact(Integer id) {
		contactRepository.deleteById(id);
		return id;

	}

	@Override
	public List<Contact> findAll() {
		return (List<Contact>)contactRepository.findAll();
	}

	@Override
	public Integer editContact(Contact editContactRequest) {
		Contact editContact = contactRepository.save(fromEditContactRequest(editContactRequest));
		return editContact.getId();
	}

}
