package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Contact;
import com.example.demo.exception.DemoException;
import com.example.demo.repository.ContactRepository;
import com.example.demo.rest.response.ContactResponse;
import com.example.demo.service.ContactServiceImpl;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ContactServiceTest {

	@InjectMocks
	ContactServiceImpl contactService;

	@Mock
	ContactRepository contactRepository;

	// TODO: this test is not mocking well the findAll call, maybe because the
	// SpecificationBuilder is not allowing it to mock.
//	@MockitoSettings(strictness = Strictness.WARN)
//	@Test
//	void getAllContactsTest() {
//		List<Contact> list = new ArrayList<Contact>();
//
//		AnyPageFilter pageFilter = new AnyPageFilter();
//		pageFilter.setValue("");
//		pageFilter.setPageNumber(0);
//		pageFilter.setPageSize(10);
//
//		Contact contactOne = new Contact(1, "One", "Surname1One", "Surname2One", 666555444, "contact-one@gmail.com");
//		Contact contactTwo = new Contact(2, "Two", "Surname1Two", "Surname2Two", 654321987, "contact-two@gmail.com");
//		Contact contactThree = new Contact(3, "Three", "Surname1Three", "Surname2Three", 666555444,
//				"contact-three@gmail.com");
//
//		list.add(contactOne);
//		list.add(contactTwo);
//		list.add(contactThree);
//
//		Page<Contact> page = new PageImpl<>(list);
//
//		// with mockito, we make that when the next methods (for example getContacts()
//		// that internally uses findAll) call findAll,
//		// the result will be this list insted the DB real one
//		when(contactRepository.findAll(new SpecificationImpl<>(), pageFilter.toPageable())).thenReturn(page);
//
//		// test
//		List<ContactResponse> empList = service.getContacts(pageFilter).getData();
//
//		assertEquals(3, empList.size());
//	}

	@Test
	void getOneContactTest() {
		when(contactRepository.findById(1)).thenReturn(
				Optional.of(new Contact(1, "One", "Surname1One", "Surname2One", 666555444, "contact-one@gmail.com")));

		ContactResponse contact = contactService.getContact(1).getData();

		assertNotNull(contact);
	}

	@Test
	void contactNotPresentInDb() {
		when(contactRepository.findById(1)).thenReturn(Optional.empty());

		DemoException thrown = assertThrows(DemoException.class, () -> contactService.getContact(1));

		assertEquals("ID_NOT_EXISTS", thrown.getMessage());
	}

}
