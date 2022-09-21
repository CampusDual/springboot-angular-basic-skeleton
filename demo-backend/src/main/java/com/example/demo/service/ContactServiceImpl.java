package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Contact;
import com.example.demo.repository.ContactRepository;

@Service
public class ContactServiceImpl implements IContactService {

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
	public Page<Contact> getContacts(Pageable pageFilter) {
		return contactRepository.findAll(pageFilter);
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
}
