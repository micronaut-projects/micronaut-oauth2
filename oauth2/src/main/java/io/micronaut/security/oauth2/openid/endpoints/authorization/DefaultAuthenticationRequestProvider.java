/*
 * Copyright 2017-2019 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.micronaut.security.oauth2.openid.endpoints.authorization;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.security.oauth2.configuration.OauthConfiguration;
import io.micronaut.security.oauth2.openid.endpoints.authorization.state.StateFactory;

import javax.annotation.Nullable;
import javax.inject.Singleton;

/**
 * Default implementation of {@link AuthenticationRequestProvider}.
 *
 * @author Sergio del Amo
 * @since 1.0.0
 */
@Requires(beans = {OauthConfiguration.class, AuthorizationEndpointRequestConfiguration.class})
@Singleton
public class DefaultAuthenticationRequestProvider implements AuthenticationRequestProvider {

    private final OauthConfiguration oauthConfiguration;
    private final AuthorizationEndpointRequestConfiguration authorizationEndpointRequestConfiguration;
    private final StateFactory stateFactory;
    private final NonceProvider nonceProvider;
    private final LoginHintProvider loginHintProvider;
    private final IdTokenHintProvider idTokenHintProvider;

    /**
     *
     * @param oauthConfiguration OAuth 2.0 Configuration
     * @param authorizationEndpointRequestConfiguration Authorization Endpoint Request Configuration
     * @param stateFactory Authorization state provider
     * @param nonceProvider Authorization nonce provider
     * @param loginHintProvider Login Hint Provider
     * @param idTokenHintProvider Id Token Hint Provider
     */
    public DefaultAuthenticationRequestProvider(OauthConfiguration oauthConfiguration,
                                                AuthorizationEndpointRequestConfiguration authorizationEndpointRequestConfiguration,
                                                @Nullable StateFactory stateFactory,
                                                @Nullable NonceProvider nonceProvider,
                                                @Nullable LoginHintProvider loginHintProvider,
                                                @Nullable IdTokenHintProvider idTokenHintProvider) {
        this.oauthConfiguration = oauthConfiguration;
        this.authorizationEndpointRequestConfiguration = authorizationEndpointRequestConfiguration;
        this.stateFactory = stateFactory;
        this.nonceProvider = nonceProvider;
        this.loginHintProvider = loginHintProvider;
        this.idTokenHintProvider = idTokenHintProvider;
    }

    @Override
    public AuthenticationRequest generateAuthenticationRequest(HttpRequest<?> request) {
        return new AuthenticationRequestAdapter(request,
                oauthConfiguration,
                authorizationEndpointRequestConfiguration,
                stateFactory,
                nonceProvider,
                loginHintProvider,
                idTokenHintProvider);
    }
}
