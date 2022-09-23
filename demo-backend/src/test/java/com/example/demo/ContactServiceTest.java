package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.borjaglez.springify.repository.filter.impl.AnyPageFilter;
import com.borjaglez.springify.repository.specification.SpecificationImpl;
import com.example.demo.entity.Contact;
import com.example.demo.exception.DemoException;
import com.example.demo.repository.ContactRepository;
import com.example.demo.service.ContactServiceImpl;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ContactServiceTest {

	@InjectMocks
	ContactServiceImpl contactService;

	@Mock
	ContactRepository contactRepository;

	@Test
	void getAllContactsTest() {
		List<Contact> list = new ArrayList<Contact>();

		AnyPageFilter pageFilter = new AnyPageFilter();
		pageFilter.setValue("");
		pageFilter.setPageNumber(0);
		pageFilter.setPageSize(10);

		Contact contactOne = new Contact(1, "One", "Surname1One", "Surname2One", 666555444, "contact-one@gmail.com");
		Contact contactTwo = new Contact(2, "Two", "Surname1Two", "Surname2Two", 654321987, "contact-two@gmail.com");
		Contact contactThree = new Contact(3, "Three", "Surname1Three", "Surname2Three", 666555444,
				"contact-three@gmail.com");

		list.add(contactOne);
		list.add(contactTwo);
		list.add(contactThree);

		Page<Contact> contacts = new PageImpl<>(list);

		// with mockito, we make that when the next methods (for example getContacts()
		// that internally uses findAll) call findAll,
		// the result will be this list instead the DB real one
		when(this.contactRepository.findAll(any(SpecificationImpl.class), isA(Pageable.class))).thenReturn(contacts);

		// test
		List<Contact> empList = contactService.getContacts(pageFilter).getData();

		assertEquals(3, empList.size());
	}

	@Test
	void getOneContactTest() {
		when(contactRepository.findById(1)).thenReturn(
				Optional.of(new Contact(1, "One", "Surname1One", "Surname2One", 666555444, "contact-one@gmail.com")));

		Contact contact = contactService.getContact(1);

		assertNotNull(contact);
	}

	@Test
	void contactNotPresentInDb() {
		when(contactRepository.findById(1)).thenReturn(Optional.empty());
		assertNull(contactService.getContact(1));

	}

	@Test
	void addContactTest() {
		Contact createContactRequest = new Contact("One", "Surname1One", "Surname2One",
				666555444, "contact-one@gmail.com");
		Contact contact = contactService.fromCreateContactRequest(createContactRequest);
		contact.setId(1);
		
		when(contactRepository.save(any(Contact.class))).thenReturn(contact);

		Integer newContactId = contactService.createContact(createContactRequest).getId();

		assertNotNull(newContactId);
		assertEquals(1, newContactId);
	}

	@Test
	void editContactTest() {
		Contact editContactRequest = new Contact(1, "OneEdit", "Surname1OneEdit", "Surname2OneEdit",
				666555444, "contact-one-edit@gmail.com");
		Contact contact = contactService.fromCreateContactRequest(editContactRequest);
		contact.setId(1);
		
		when(contactRepository.save(any(Contact.class))).thenReturn(contact);

		Integer editContactId = contactService.editContact(editContactRequest);

		assertNotNull(editContactId);
		assertEquals(1, editContactId);
	}
	
	@Test
	void deleteContactTest() {
		Integer result = contactService.deleteContact(1);

		assertNotNull(result);
	}

}
