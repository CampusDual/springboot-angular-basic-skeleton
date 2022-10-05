package com.example.demo.service;

import org.hibernate.criterion.Restrictions;

import com.borjaglez.springify.repository.filter.IPageFilter;
import com.example.demo.dto.ContactDTO;
import com.example.demo.entity.Contact;
import com.example.demo.exception.DemoException;
import com.example.demo.rest.model.QuerySortPaginationRequest;
import com.example.demo.utils.Constant;

public class AbstractDemoService {
	protected void checkInputParams(IPageFilter pageFilter) {
		if (pageFilter.getPageNumber() == null) {
			throw new DemoException(Constant.PAGE_INDEX_REQUIRED);
		}
		if (pageFilter.getPageSize() == null) {
			throw new DemoException(Constant.PAGE_SIZE_REQUIRED);
		}
	}
	
	protected void checkInputParams(QuerySortPaginationRequest pageFilter) {
		if (pageFilter.getPageIndex() == null) {
			throw new DemoException(Constant.PAGE_INDEX_REQUIRED);
		}
		if (pageFilter.getPageSize() == null) {
			throw new DemoException(Constant.PAGE_SIZE_REQUIRED);
		}
	}
	
	public Contact fromEditContactRequest(Contact contactRequest) {
		return new Contact(contactRequest.getId(), contactRequest.getName(), contactRequest.getSurname1(),
				contactRequest.getSurname2(), contactRequest.getPhone(), contactRequest.getEmail());
	}
<<<<<<< Updated upstream
=======
	
	public Contact fromCreateContactRequest(ContactDTO contactRequest) {
		return  new Contact(contactRequest.getName(), contactRequest.getSurname1(), contactRequest.getSurname2(),
				contactRequest.getPhone(), contactRequest.getEmail());
	}
>>>>>>> Stashed changes
}
