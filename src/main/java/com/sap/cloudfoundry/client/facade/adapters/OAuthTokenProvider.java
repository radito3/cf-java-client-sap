package com.sap.cloudfoundry.client.facade.adapters;

import org.cloudfoundry.reactor.ConnectionContext;
import org.cloudfoundry.reactor.TokenProvider;

import com.sap.cloudfoundry.client.facade.oauth2.OAuth2AccessTokenWithAdditionalInfo;
import com.sap.cloudfoundry.client.facade.oauth2.OAuthClient;

import reactor.core.publisher.Mono;

public class OAuthTokenProvider implements TokenProvider {

    private final OAuthClient oAuthClient;

    public OAuthTokenProvider(OAuthClient oAuthClient) {
        this.oAuthClient = oAuthClient;
    }

    @Override
    public Mono<String> getToken(ConnectionContext connectionContext) {
        return Mono.fromSupplier(() -> {
            OAuth2AccessTokenWithAdditionalInfo token = oAuthClient.getToken();
            return token.getAuthorizationHeaderValue();
        });
    }

}
