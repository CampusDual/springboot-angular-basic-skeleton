package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.borjaglez.springify.repository.filter.impl.AnyPageFilter;
import com.borjaglez.springify.repository.specification.SpecificationBuilder;
import com.example.demo.entity.Contact;
import com.example.demo.entity.error.CommonError;
import com.example.demo.entity.error.ContactError;
import com.example.demo.entity.messages.ContactMessages;
import com.example.demo.exception.DemoException;
import com.example.demo.repository.ContactRepository;
import com.example.demo.rest.model.CreateContactRequest;
import com.example.demo.rest.model.EditContactRequest;
import com.example.demo.rest.response.ContactResponse;
import com.example.demo.rest.response.DataSourceRESTResponse;
import com.example.demo.rest.response.RESTResponse;
import com.example.demo.rest.response.RESTResponse.Code;

@Service
public class ContactServiceImpl extends AbstractDemoService implements ContactService {

	/**
	 * Especificación JPA para {@link Contact}.
	 */
	@Autowired
	private ContactRepository contactRepository;

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(readOnly = true)
	public RESTResponse<ContactResponse> getContact(Integer id) {
		Optional<Contact> optContact = contactRepository.findById(id);
		if (!optContact.isPresent()) {
			throw new DemoException(CommonError.ID_NOT_EXISTS.toString());
		}
		return new RESTResponse<>(new ContactResponse(optContact.get()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(readOnly = true)
	public DataSourceRESTResponse<List<ContactResponse>> getContacts(AnyPageFilter pageFilter) {
		checkInputParams(pageFilter);
		Page<Contact> contacts = SpecificationBuilder.selectDistinctFrom(contactRepository).where(pageFilter)
				.findAll(pageFilter);
		return new DataSourceRESTResponse<>((int) contacts.getTotalElements(),
				contacts.getContent().stream().map(ContactResponse::new).collect(Collectors.toList()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public RESTResponse<Integer> createContact(CreateContactRequest createContactRequest) {
		try {
			checkInputParams(createContactRequest);
			Contact newContact = contactRepository.save(fromCreateContactRequest(createContactRequest));
//			contactRepository.flush();
			return new RESTResponse<>(Code.OK, ContactMessages.CONTACT_CREATE_SUCCESS.toString(), newContact.getId());
		} catch (DataIntegrityViolationException e) {
			if (e.getMessage().contains("contacts_unique")) {
				throw new DemoException(ContactError.CONTACT_ALREADY_EXISTS.toString());
			}
			throw new DemoException(e.getMessage());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public RESTResponse<Integer> editContact(EditContactRequest editContactRequest) {
		try {
			checkInputParams(editContactRequest);
			Contact editContact = contactRepository.save(fromEditContactRequest(editContactRequest));
//			contactRepository.flush();
			return new RESTResponse<>(Code.OK, ContactMessages.CONTACT_EDIT_SUCCESS.toString(), editContact.getId());
		} catch (DataIntegrityViolationException e) {
			throw new DemoException(CommonError.ID_NOT_EXISTS.toString());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public RESTResponse<Integer> deleteContact(Integer id) {
		try {
			contactRepository.deleteById(id);
//			contactRepository.flush();
			return new RESTResponse<>(Code.OK, ContactMessages.CONTACT_DELETE_SUCCESS.toString(), id);
		} catch (EmptyResultDataAccessException e) {
			if (e.getCause() instanceof ConstraintViolationException) {
				throw new DemoException(ContactError.CONTACT_CONSTRAINT_VIOLATION.toString());
			} else {
				throw new DemoException(CommonError.ID_NOT_EXISTS.toString());
			}
		}
	}
}
