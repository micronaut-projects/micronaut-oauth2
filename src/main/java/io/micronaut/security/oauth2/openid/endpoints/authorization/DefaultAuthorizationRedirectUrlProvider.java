/*
 * Copyright 2017-2018 original authors
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
import io.micronaut.http.uri.UriTemplate;
import io.micronaut.security.oauth2.openid.configuration.OpenIdProviderMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.annotation.Nonnull;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Default implementation of {@link io.micronaut.security.oauth2.openid.endpoints.authorization.AuthorizationRedirectUrlProvider}.
 *
 * @author Sergio del Amo
 * @version 1.1.0
 */
@Singleton
@Requires(beans = {AuthenticationRequestProvider.class, OpenIdProviderMetadata.class})
public class DefaultAuthorizationRedirectUrlProvider implements AuthorizationRedirectUrlProvider {

    private static final char SPACE = ' ';
    private static final char OPENCURLYBRACE = '{';
    private static final char COMMA = ',';
    private static final char CLOSECURLYBRACE = '}';
    private static final char QUESTIONMARK = '?';

    private static final Logger LOG = LoggerFactory.getLogger(DefaultAuthorizationRedirectUrlProvider.class);

    @Nonnull
    private final AuthenticationRequestProvider authenticationRequestProvider;

    @Nonnull
    private final OpenIdProviderMetadata openIdProviderMetadata;

    /**
     *
     * @param authenticationRequestProvider Authentication Request provider
     * @param openIdProviderMetadata OpenID provider metadata.
     */
    public DefaultAuthorizationRedirectUrlProvider(AuthenticationRequestProvider authenticationRequestProvider,
                                                   OpenIdProviderMetadata openIdProviderMetadata) {
        this.authenticationRequestProvider = authenticationRequestProvider;
        this.openIdProviderMetadata = openIdProviderMetadata;
    }

    /**
     *
     * @return A URL to redirect the user to the OpenID Provider authorization endpoint.
     */
    @Override
    public String getAuthorizationRedirectUrl() {
        AuthenticationRequest authenticationRequest = authenticationRequestProvider.generateAuthenticationRequest();
        Map<String, Object> arguments = instantiateParameters(authenticationRequest);
        String baseUrl = this.openIdProviderMetadata.getAuthorizationEndpoint();
        String expandedUri = expandedUri(baseUrl, arguments);
        if (LOG.isDebugEnabled()) {
            LOG.debug("authorization redirect url {}", expandedUri);
        }
        return expandedUri;
    }

    /**
     * //TODO Replace this once this is merged: https://github.com/micronaut-projects/micronaut-core/pull/931 .
     * @param baseUrl Base Url
     * @param queryParams Query Parameters
     * @return The Expanded URI
     */
    protected String expandedUri(@Nonnull String baseUrl,
                                 @Nonnull Map<String, Object> queryParams) {
        Optional<String> optionalUrlArguments = queryParams.keySet().stream().reduce((a, b) -> a + COMMA + b);
        String template = baseUrl + (optionalUrlArguments.map(s -> OPENCURLYBRACE + QUESTIONMARK + s + CLOSECURLYBRACE).orElse(""));
        UriTemplate uriTemplate = new UriTemplate(template);
        return uriTemplate.expand(queryParams);
    }

    /**
     *
     * @param authenticationRequest Authentication Request
     * @return A parameter map which contains the URL variables used to construct the authorization redirect url.
     */
    protected Map<String, Object> instantiateParameters(AuthenticationRequest authenticationRequest) {
        Map<String, Object> parameters = new HashMap<>();
        populateScope(authenticationRequest, parameters);
        populateResponseType(authenticationRequest, parameters);
        populateClientId(authenticationRequest, parameters);
        populateRedirectUri(authenticationRequest, parameters);
        populateState(authenticationRequest, parameters);
        populateResponseMode(authenticationRequest, parameters);
        populateNonce(authenticationRequest, parameters);
        populateDisplay(authenticationRequest, parameters);
        populatePrompt(authenticationRequest, parameters);
        populateMaxAge(authenticationRequest, parameters);
        populateUiLocales(authenticationRequest, parameters);
        populateIdTokenHint(authenticationRequest, parameters);
        populateLoginHint(authenticationRequest, parameters);
        populateAcrValues(authenticationRequest, parameters);
        return parameters;
    }

    /**
     *
     * @param authenticationRequest Authentication Request
     * @param parameters Authentication Request Parameters
     */
    protected void populateScope(@Nonnull AuthenticationRequest authenticationRequest,
                                 @Nonnull Map<String, Object> parameters) {
        Optional<String> optionalStr = authenticationRequest.getScopes().stream().reduce((a, b) -> a + SPACE + b);
        parameters.put(AuthenticationRequest.PARAMETER_SCOPE, optionalStr.orElse(AuthorizationEndpointConfigurationProperties.DEFAULT_SCOPE));
    }

    /**
     *
     * @param authenticationRequest Authentication Request
     * @param parameters Authentication Request Parameters
     */
    protected void populateResponseType(@Nonnull AuthenticationRequest authenticationRequest,
                                        @Nonnull Map<String, Object> parameters) {
        parameters.put(AuthenticationRequest.PARAMETER_RESPONSE_TYPE, authenticationRequest.getResponseType());
    }

    /**
     *
     * @param authenticationRequest Authentication Request
     * @param parameters Authentication Request Parameters
     */
    protected void populateClientId(@Nonnull AuthenticationRequest authenticationRequest,
                                    @Nonnull Map<String, Object> parameters) {
        parameters.put(AuthenticationRequest.PARAMETER_CLIENT_ID, authenticationRequest.getClientId());
    }

    /**
     *
     * @param authenticationRequest Authentication Request
     * @param parameters Authentication Request Parameters
     */
    protected void populateRedirectUri(@Nonnull AuthenticationRequest authenticationRequest,
                                       @Nonnull Map<String, Object> parameters) {
        parameters.put(AuthenticationRequest.PARAMETER_REDIRECT_URI, authenticationRequest.getRedirectUri());
    }

    /**
     *
     * @param authenticationRequest Authentication Request
     * @param parameters Authentication Request Parameters
     */
    protected void populateState(@Nonnull AuthenticationRequest authenticationRequest,
                                 @Nonnull Map<String, Object> parameters) {
        parameters.put(AuthenticationRequest.PARAMETER_STATE, authenticationRequest.getState());
    }

    /**
     *
     * @param authenticationRequest Authentication Request
     * @param parameters Authentication Request Parameters
     */
    protected void populateResponseMode(@Nonnull AuthenticationRequest authenticationRequest,
                                        @Nonnull Map<String, Object> parameters) {
        if (authenticationRequest.getResponseMode() != null) {
            parameters.put(AuthenticationRequest.PARAMETER_RESPONSE_MODE, authenticationRequest.getResponseMode());
        }
    }

    /**
     *
     * @param authenticationRequest Authentication Request
     * @param parameters Authentication Request Parameters
     */
    protected void populateNonce(@Nonnull AuthenticationRequest authenticationRequest,
                                 @Nonnull Map<String, Object> parameters) {
        if (authenticationRequest.getNonce() != null) {
            parameters.put(AuthenticationRequest.PARAMETER_NONCE, authenticationRequest.getNonce());
        }
    }

    /**
     *
     * @param authenticationRequest Authentication Request
     * @param parameters Authentication Request Parameters
     */
    protected void populateDisplay(@Nonnull AuthenticationRequest authenticationRequest,
                                   @Nonnull Map<String, Object> parameters) {
        if (authenticationRequest.getDisplay() != null) {
            parameters.put(AuthenticationRequest.PARAMETER_DISPLAY, authenticationRequest.getDisplay());
        }
    }

    /**
     *
     * @param authenticationRequest Authentication Request
     * @param parameters Authentication Request Parameters
     */
    protected void populatePrompt(@Nonnull AuthenticationRequest authenticationRequest,
                                  @Nonnull Map<String, Object> parameters) {
        if (authenticationRequest.getPrompt() != null) {
            parameters.put(AuthenticationRequest.PARAMETER_PROMPT, authenticationRequest.getPrompt());
        }
    }

    /**
     *
     * @param authenticationRequest Authentication Request
     * @param parameters Authentication Request Parameters
     */
    protected void populateMaxAge(@Nonnull AuthenticationRequest authenticationRequest,
                                  @Nonnull Map<String, Object> parameters) {
        if (authenticationRequest.getMaxAge() != null) {
            parameters.put(AuthenticationRequest.PARAMETER_MAX_AGE, authenticationRequest.getMaxAge());
        }
    }

    /**
     *
     * @param authenticationRequest Authentication Request
     * @param parameters Authentication Request Parameters
     */
    protected void populateUiLocales(@Nonnull AuthenticationRequest authenticationRequest,
                                     @Nonnull Map<String, Object> parameters) {
        if (authenticationRequest.getUiLocales() != null) {
            Optional<String> optionalUiLocales = authenticationRequest.getUiLocales().stream().reduce((a, b) -> a + SPACE + b);
            optionalUiLocales.ifPresent(uiLocales -> parameters.put(AuthenticationRequest.PARAMETER_UI_LOCALES, uiLocales));
        }
    }

    /**
     *
     * @param authenticationRequest Authentication Request
     * @param parameters Authentication Request Parameters
     */
    protected void populateIdTokenHint(@Nonnull AuthenticationRequest authenticationRequest,
                                       @Nonnull Map<String, Object> parameters) {
        if (authenticationRequest.getIdTokenHint() != null) {
            parameters.put(AuthenticationRequest.PARAMETER_ID_TOKEN_HINT, authenticationRequest.getIdTokenHint());
        }
    }

    /**
     *
     * @param authenticationRequest Authentication Request
     * @param parameters Authentication Request Parameters
     */
    protected void populateLoginHint(@Nonnull AuthenticationRequest authenticationRequest,
                                     @Nonnull Map<String, Object> parameters) {
        if (authenticationRequest.getLoginHint() != null) {
            parameters.put(AuthenticationRequest.PARAMETER_LOGIN_HINT, authenticationRequest.getLoginHint());
        }
    }

    /**
     *
     * @param authenticationRequest Authentication Request
     * @param parameters Authentication Request Parameters
     */
    protected void populateAcrValues(@Nonnull AuthenticationRequest authenticationRequest,
                                     @Nonnull Map<String, Object> parameters) {
        if (authenticationRequest.getAcrValues() != null) {
            Optional<String> optionalAcrValues = authenticationRequest.getAcrValues().stream().reduce((a, b) -> a + SPACE + b);
            optionalAcrValues.ifPresent(acrValues -> parameters.put(AuthenticationRequest.PARAMETER_ACR_VALUES, acrValues));
        }
    }
}
