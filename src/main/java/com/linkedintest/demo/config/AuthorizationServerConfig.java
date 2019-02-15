package com.linkedintest.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private final String clientId = "client-test";
    private final String secret = "secret-test";
    private final String roleClient = "ROLE_CLIENT";
    private final String roleTrustedClient = "ROLE_TRUSTED_CLIENT";
    private final String scopeRead = "read";
    private final String scopeWrite = "write";
    private final String scopeTrust = "trust";
    private final String resourceId = "oauth2-resource";

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception{
        security.checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception{
        clients.inMemory().withClient("{id}"+clientId)
                .authorizedGrantTypes("client_credentials", "password")
                .authorities(roleClient, roleTrustedClient)
                .scopes(scopeRead, scopeWrite, scopeTrust)
                .resourceIds(resourceId)
                .accessTokenValiditySeconds(600)
                .secret("{noop}" + secret);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception{
        endpoints.authenticationManager(authenticationManager);
    }

}
