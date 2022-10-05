package com.example.demo.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
	UserMapper INSTANCE =  Mappers.getMapper( UserMapper.class);
	

}
