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

import io.micronaut.core.async.SupplierUtil;
import io.micronaut.http.HttpRequest;
import io.micronaut.security.oauth2.configuration.OauthConfiguration;
import io.micronaut.security.oauth2.openid.endpoints.authorization.state.StateFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * Provides an {@link AuthenticationRequest} by combining {@link OauthConfiguration},
 * {@link StateFactory}, {@link NonceProvider}, {@link AuthorizationEndpoint}.
 * @author Sergio del Amo
 * @since 1.0.0
 */
public class AuthenticationRequestAdapter implements AuthenticationRequest {

    private OauthConfiguration oauthConfiguration;
    private StateFactory stateFactory;
    private NonceProvider nonceProvider;
    private LoginHintProvider loginHintProvider;
    private IdTokenHintProvider idTokenHintProvider;
    private AuthorizationEndpointRequestConfiguration authorizationEndpointConfiguration;
    private final Supplier<String> stateSupplier;

    /**
     *
     * @param request The original request prior redirect.
     * @param oauthConfiguration OAuth 2.0 Configuration
     * @param authorizationEndpointConfiguration Authorization Endpoint Configuration
     * @param stateFactory State Provider
     * @param nonceProvider Nonce Provider
     * @param loginHintProvider Login Hint Provider
     * @param idTokenHintProvider Id Token Hint Provider
     */
    public AuthenticationRequestAdapter(HttpRequest<?> request,
                                        OauthConfiguration oauthConfiguration,
                                        AuthorizationEndpointRequestConfiguration authorizationEndpointConfiguration,
                                        @Nullable StateFactory stateFactory,
                                        @Nullable NonceProvider nonceProvider,
                                        @Nullable LoginHintProvider loginHintProvider,
                                        @Nullable IdTokenHintProvider idTokenHintProvider
                                        ) {
        this.oauthConfiguration = oauthConfiguration;
        this.authorizationEndpointConfiguration = authorizationEndpointConfiguration;
        this.stateFactory = stateFactory;
        this.nonceProvider = nonceProvider;
        this.loginHintProvider = loginHintProvider;
        this.idTokenHintProvider = idTokenHintProvider;
        this.stateSupplier = SupplierUtil.memoized(() ->
            getStateFactory().map(sf -> sf.buildState(request)).orElse(null));
    }

    @Override
    @Nonnull
    public String getClientId() {
        return getOauthConfiguration().getClientId();
    }

    @Override
    @Nullable
    public String getState() {
        return stateSupplier.get();
    }

    @Nullable
    @Override
    public String getNonce() {
        return getNonceProvider().map(NonceProvider::generateNonce).orElse(null);
    }

    @Override
    @Nonnull
    public List<String> getScopes() {
        return getAuthorizationEndpointConfiguration().getScopes();
    }

    @Nonnull
    @Override
    public String getResponseType() {
        return getAuthorizationEndpointConfiguration().getResponseType();
    }

    @Nullable
    @Override
    public String getRedirectUri() {
        return getAuthorizationEndpointConfiguration().getRedirectUri();
    }

    @Nullable
    @Override
    public String getResponseMode() {
        return getAuthorizationEndpointConfiguration().getResponseMode();
    }

    @Nullable
    @Override
    public Display getDisplay() {
        return getAuthorizationEndpointConfiguration().getDisplay();
    }

    @Nullable
    @Override
    public Prompt getPrompt() {
        return getAuthorizationEndpointConfiguration().getPrompt();
    }

    @Nullable
    @Override
    public Integer getMaxAge() {
        return getAuthorizationEndpointConfiguration().getMaxAge();
    }

    @Nullable
    @Override
    public List<String> getUiLocales() {
        return getAuthorizationEndpointConfiguration().getUiLocales();
    }

    @Nullable
    @Override
    public String getIdTokenHint() {
        return getIdTokenHintProvider().map(IdTokenHintProvider::resolveIdTokenHint).orElse(null);
    }

    @Nullable
    @Override
    public String getLoginHint() {
        return getLoginHintProvider().map(LoginHintProvider::resolveLoginHint).orElse(null);
    }

    @Nullable
    @Override
    public List<String> getAcrValues() {
        return getAuthorizationEndpointConfiguration().getAcrValues();
    }

    private OauthConfiguration getOauthConfiguration() {
        return oauthConfiguration;
    }

    private Optional<IdTokenHintProvider> getIdTokenHintProvider() {
        return Optional.ofNullable(idTokenHintProvider);
    }

    private Optional<LoginHintProvider> getLoginHintProvider() {
        return Optional.ofNullable(loginHintProvider);
    }

    private Optional<StateFactory> getStateFactory() {
        return Optional.ofNullable(stateFactory);
    }

    private Optional<NonceProvider> getNonceProvider() {
        return Optional.ofNullable(nonceProvider);
    }

    private AuthorizationEndpointRequestConfiguration getAuthorizationEndpointConfiguration() {
        return authorizationEndpointConfiguration;
    }
}
