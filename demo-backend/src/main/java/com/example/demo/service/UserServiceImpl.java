package com.example.demo.service;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Section;
import com.example.demo.entity.User;
import com.example.demo.entity.enums.SectionsEnum;
import com.example.demo.exception.DemoException;
import com.example.demo.repository.UserRepository;
import com.example.demo.utils.Constant;


@Service
public class UserServiceImpl implements IUserService {

	/**
	 * Especificación JPA para {@link User}.
	 */
	@Autowired
	private UserRepository userRepository;


	@Override
	@Transactional(readOnly = true)
	public Boolean canLogin(String user) {
		Optional<User> optUser = userRepository.findByLogin(user);
		if (!optUser.isPresent()) {
			throw new DemoException(Constant.USER_NOT_EXISTS.toString());
		}
		if (optUser.get().getSections().isEmpty()) {
			throw new DemoException(Constant.NO_SECTIONS_ACCESS.toString());
		}
		if (Collections.disjoint(
				optUser.get().getSections().stream().map(Section::getAlias).collect(Collectors.toList()),
				EnumSet.allOf(SectionsEnum.class).stream().map(SectionsEnum::toString)
						.collect(Collectors.toList()))) {
			throw new DemoException(Constant.NO_SECTIONS_ACCESS.toString());
		}
		return true;
	}
}
