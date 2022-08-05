package com.example.demo.service;

import org.apache.commons.lang3.StringUtils;

import com.borjaglez.springify.repository.filter.IPageFilter;
import com.example.demo.entity.Contact;
import com.example.demo.entity.error.ContactError;
import com.example.demo.entity.error.QuerySortPaginationError;
import com.example.demo.exception.DemoException;
import com.example.demo.rest.model.CreateContactRequest;
import com.example.demo.rest.model.EditContactRequest;
import com.example.demo.rest.model.QuerySortPaginationRequest;

public abstract class AbstractDemoService {

	protected void checkInputParams(IPageFilter pageFilter) {
		if (pageFilter.getPageNumber() == null) {
			throw new DemoException(QuerySortPaginationError.PAGE_INDEX_REQUIRED.toString());
		}
		if (pageFilter.getPageSize() == null) {
			throw new DemoException(QuerySortPaginationError.PAGE_SIZE_REQUIRED.toString());
		}
	}

	protected void checkInputParams(QuerySortPaginationRequest pageFilter) {
		if (pageFilter.getPageIndex() == null) {
			throw new DemoException(QuerySortPaginationError.PAGE_INDEX_REQUIRED.toString());
		}
		if (pageFilter.getPageSize() == null) {
			throw new DemoException(QuerySortPaginationError.PAGE_SIZE_REQUIRED.toString());
		}
	}

	protected void checkInputParams(CreateContactRequest createContactRequest) {
		if (StringUtils.isBlank(createContactRequest.getName())) {
			throw new DemoException(ContactError.NAME_REQUIRED.toString());
		}
		if (StringUtils.isBlank(createContactRequest.getSurname1())) {
			throw new DemoException(ContactError.SURNAME1_REQUIRED.toString());
		}
		if (createContactRequest.getPhone() == null) {
			throw new DemoException(ContactError.PHONE_REQUIRED.toString());
		}
		if (createContactRequest.getPhone() > 999999999) {
			throw new DemoException(ContactError.PHONE_INVALID.toString());
		}
	}

	protected void checkInputParams(EditContactRequest editContactRequest) {
		if (editContactRequest.getId() == null) {
			throw new DemoException(ContactError.ID_REQUIRED.toString());
		}
		checkInputParams((CreateContactRequest) editContactRequest);
	}

	public Contact fromEditContactRequest(EditContactRequest contactRequest) {
		return new Contact(contactRequest.getId(), contactRequest.getName(), contactRequest.getSurname1(),
				contactRequest.getSurname2(), contactRequest.getPhone(), contactRequest.getEmail());

	}

	public Contact fromCreateContactRequest(CreateContactRequest contactRequest) {
		return new Contact(contactRequest.getName(), contactRequest.getSurname1(), contactRequest.getSurname2(),
				contactRequest.getPhone(), contactRequest.getEmail());
	}

}
