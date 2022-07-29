package com.example.demo.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import com.example.demo.auth.provider.DemoAuthenticationProvider;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DemoAuthenticationProvider demoAuthenticationProvider;
	
    @Override
	@Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
	
	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) {
		authenticationManagerBuilder.authenticationProvider(demoAuthenticationProvider);
	}
	
	 @Override
	    public void configure(WebSecurity web) throws Exception {
	        web
	          .ignoring()
	            .antMatchers(HttpMethod.OPTIONS,"/**")
	            .antMatchers("/login/**")
	            ;
	          //URL you want to ignore
	    }

	@Override
	    protected void configure(HttpSecurity http) throws Exception {
	        // Disable CSRF (cross site request forgery)
	        http.csrf().disable();

	        // No session will be created or used by spring security
	        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

	        // Entry points
	        http.authorizeRequests()
	                .antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
	                // Disallow everything else..
	                .anyRequest().authenticated();

	    }

}