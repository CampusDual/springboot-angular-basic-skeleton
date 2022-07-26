package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Contact;

public interface ContactRepository extends CrudRepository<Contact, Integer> {

}
