package com.example.demo.dto.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.demo.dto.ContactDTO;
import com.example.demo.entity.Contact;

/*
 *Clase para mapear un objeto de DTO a entidad y viceversa.
 *Se utiliza la libreria mapstruct.
 *
 *IMPORTANTE: 
 *	para que genere el código imprescindible:
 * 	hacer maven clean y maven install 
 *	antes de arrancar el proyecto y cada vez que se realizan cambios en entidad o dto
 */
@Mapper
public interface ContactMapper {

    ContactMapper INSTANCE = Mappers.getMapper( ContactMapper.class );
 
    ContactDTO contactToContactDto(Contact contact);
    
    List<ContactDTO> contactToContactDtoList(List<Contact> contacts);
    
    Contact contactDTOtoContact(ContactDTO contactdto);


}
