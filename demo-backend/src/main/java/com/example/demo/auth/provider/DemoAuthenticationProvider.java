package com.example.demo.auth.provider;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.example.demo.entity.Section;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.utils.CipherUtils;


@Component
public class DemoAuthenticationProvider implements AuthenticationProvider  {

	@Autowired
	private UserRepository userRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(DemoAuthenticationProvider.class);
	
    @Override
    @Transactional
    public Authentication authenticate(Authentication authentication) 
      throws AuthenticationException {
        return getUserInfo(authentication.getName(), authentication.getCredentials().toString());
    }

    private UsernamePasswordAuthenticationToken getUserInfo(String userName, String password) {
    	CipherUtils cipher = new CipherUtils();
		Set<GrantedAuthority> listAuthorities = new HashSet<>();
		User user = new User();
		Optional<User> optUser = userRepository.findByLogin(userName);
		if (!optUser.isPresent()) {
			logger.error("The user not exists in database");
			throw new BadCredentialsException("USER_NOT_EXISTS");
		} else {
			user = optUser.get();
		if (user.getPassword().equals(cipher.encrypt(userName, password))) {
			for (Section section : user.getSections()) {
				listAuthorities.add(new SimpleGrantedAuthority(section.getAlias()));
			}
			return new UsernamePasswordAuthenticationToken(userName, password, listAuthorities);
		} else {
			logger.error("Wrong password");
			throw new BadCredentialsException("WRONG_PASSWORD");
		}
	}
    }
    
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }  
}