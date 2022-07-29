package com.example.demo.auth.config;

import java.util.Arrays;
import java.util.List;

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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

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
	
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.cors()
//            .and()
//              .authorizeRequests()
//                .antMatchers(HttpMethod.GET, "/**")
//                  .hasAuthority("SCOPE_read")
//                .antMatchers(HttpMethod.POST, "/**")
//                  .hasAuthority("SCOPE_write")
//                .anyRequest()
//                  .authenticated();
//    }
	
	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.cors().and();
//	}
	
//	@Override
//	  protected void configure(HttpSecurity httpSecurity) throws Exception {
//	    httpSecurity.cors().configurationSource(request -> {
//	      var cors = new CorsConfiguration();
//	      cors.setAllowedOrigins(List.of("http://localhost:4201"));
//	      cors.setAllowedMethods(List.of("GET","POST", "PUT", "DELETE", "OPTIONS"));
//	      cors.setAllowedHeaders(List.of("*"));
//	      return cors;
//	    }).and();
//	  }
	
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//          .inMemoryAuthentication()
//          .withUser("user")
//          .password("password")
//          .roles("USER");
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//          .authorizeRequests()
//          .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//          .antMatchers("/login").permitAll()
//          .anyRequest()
//          .authenticated()
//          .and()
//          .httpBasic();        
//    }
	
//	@Override
//	public void configure(WebSecurity web) throws Exception {
//	    web.ignoring().antMatchers("/login/**");
//	}
	
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


	        // If a user try to access a resource without having enough permissions
	        //http.exceptionHandling().accessDeniedPage("/login");

	        // Apply JWT
//	        http.apply(new JwtTokenFilterConfigurer(jwtTokenProvider));

	        // Optional, if you want to test the API from a browser
	        // http.httpBasic();
	    }

}