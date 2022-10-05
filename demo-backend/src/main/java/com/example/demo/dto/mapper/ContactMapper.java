package com.example.demo.dto.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.demo.dto.ContactDTO;
import com.example.demo.entity.Contact;

@Mapper
public interface ContactMapper {
 
    ContactMapper INSTANCE = Mappers.getMapper( ContactMapper.class );
 
    ContactDTO contactToContactDto(Contact contact);
    
    List<ContactDTO> contactToContactDtoList(List<Contact> contacts);
}
