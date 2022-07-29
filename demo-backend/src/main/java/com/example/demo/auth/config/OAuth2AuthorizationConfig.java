package com.example.demo.auth.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

	@Value("${security.oauth2.client.client-id}")
	private String clientId;
	
	@Value("${security.oauth2.client.client-secret}")
	private String clientSecret;
	
	@Value("${security.oauth2.client.authorized-grant-types}")
	private String clientAuthorizedGrantTypes;
	
	@Value("#{'${security.oauth2.client.scope}'.split(',')}")
	private List<String> clientScope;

	@Value("${demo.oauth.accessTokenValiditySeconds:600}")
	private int accessTokenValiditySeconds;
	
	@Value("${demo.oauth.refreshTokenValiditySeconds:1800}")
	private int refreshTokenValiditySeconds;
	
	
	@Autowired
	private TokenStore tokenStore;
	
	@Autowired
	JwtAccessTokenConverter accessTokenConverter;

	@Autowired
	private AuthenticationManager authenticationManager;
	
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer
          .tokenKeyAccess("permitAll()")
          .checkTokenAccess("permitAll()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    	
        clients
        	.inMemory()
        	.withClient(clientId)
        	.secret(new BCryptPasswordEncoder().encode(clientSecret))
        	// "password", "authorization_code", "refresh_code", "implicit"
        	.authorizedGrantTypes("password", "refresh_token")
        	// "read", "write", "trust"
        	.scopes("read", "write", "trust")
			.accessTokenValiditySeconds(accessTokenValiditySeconds)
			.refreshTokenValiditySeconds(refreshTokenValiditySeconds);    
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
            .tokenStore(tokenStore)
            .accessTokenConverter(accessTokenConverter)
        	.authenticationManager(authenticationManager);
    }
    
}