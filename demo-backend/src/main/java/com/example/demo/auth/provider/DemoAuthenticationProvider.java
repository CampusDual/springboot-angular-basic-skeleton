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


@Component
public class DemoAuthenticationProvider implements AuthenticationProvider  {

	@Autowired
	private UserRepository userRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(DemoAuthenticationProvider.class);
	
    @Override
    @Transactional
    public Authentication authenticate(Authentication authentication) 
      throws AuthenticationException {
 
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        
        return getUserInfo(name, password);
    }

    private UsernamePasswordAuthenticationToken getUserInfo(String userName, String password) {
		Set<GrantedAuthority> listAuthorities = new HashSet<>();
		Optional<User> user = userRepository.findByLogin(userName);
		if (!user.isPresent()) {
			logger.error("The user not exists in database");
			throw new BadCredentialsException("USER_NOT_EXISTS");
		} else {
			for (Section section : user.get().getSections()) {
				listAuthorities.add(new SimpleGrantedAuthority(section.getAlias()));
			}
		}
		return new UsernamePasswordAuthenticationToken(userName, password, listAuthorities);
    }
    
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }  
}