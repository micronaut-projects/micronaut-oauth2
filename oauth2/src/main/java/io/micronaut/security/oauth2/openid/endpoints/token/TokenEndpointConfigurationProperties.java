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

package io.micronaut.security.oauth2.openid.endpoints.token;

import io.micronaut.context.annotation.ConfigurationProperties;
import io.micronaut.http.MediaType;
import io.micronaut.security.oauth2.configuration.OauthConfigurationProperties;
import io.micronaut.security.oauth2.grants.GrantType;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * {@link ConfigurationProperties} implementation of {@link TokenEndpoint}.
 *
 * @author Sergio del Amo
 * @since 1.0.0
 */
@ConfigurationProperties(TokenEndpointConfigurationProperties.PREFIX)
public class TokenEndpointConfigurationProperties implements TokenEndpoint {

    public static final String PREFIX = OauthConfigurationProperties.PREFIX + ".token";

    /**
     * Default Grant Type.
     */
    @SuppressWarnings("WeakerAccess")
    public static final String DEFAULT_GRANTTYPE = GrantType.AUTHORIZATION_CODE.getGrantType();

    /**
     * Default Grant Type.
     */
    @SuppressWarnings("WeakerAccess")
    public static final MediaType DEFAULT_CONTENT_TYPE = MediaType.APPLICATION_FORM_URLENCODED_TYPE;

    private final AuthMethodProvider authMethodProvider;

    private String url;
    private String grantType = DEFAULT_GRANTTYPE;
    private String authMethod;
    private MediaType contentType = DEFAULT_CONTENT_TYPE;
    private String redirectUri;

    /**
     *
     * @param authMethodProvider Default auth method provider
     */
    public TokenEndpointConfigurationProperties(@Nullable AuthMethodProvider authMethodProvider) {
        this.authMethodProvider = authMethodProvider;
    }

    @Nonnull
    @Override
    public String getGrantType() {
        return grantType;
    }

    @Nullable
    @Override
    public String getAuthMethod() {
        return authMethod != null ? authMethod : (authMethodProvider != null ? authMethodProvider.findAuthMethod().getAuthMethod() : null);
    }

    @Nullable
    @Override
    public String getRedirectUri() {
        return redirectUri;
    }

    /**
     * OAuth 2.0 Grant Type. Default value (authorization_code).
     * @param grantType OAuth 2.0 Grant Type
     */
    public void setGrantType(@Nonnull String grantType) {
        this.grantType = grantType;
    }

    /**
     * Client Authentication method. By default no value is specified. In that case, the value returned by {@link io.micronaut.security.oauth2.openid.endpoints.token.AuthMethodProvider}, if such a bean exists, is used.
     * @param authMethod Client Authentication method.
     */
    public void setAuthMethod(@Nullable String authMethod) {
        this.authMethod = authMethod;
    }

    /**
     *
     * @param redirectUri Redirection URI to which the response will be sent.
     */
    public void setRedirectUri(@Nullable String redirectUri) {
        this.redirectUri = redirectUri;
    }

    @Nullable
    @Override
    public String getUrl() {
        return url;
    }

    /**
     *
     * @param url token endpoint's url
     */
    public void setUrl(@Nullable String url) {
        this.url = url;
    }

    @Nonnull
    @Override
    public MediaType getContentType() {
        return this.contentType;
    }

    /**
     * The Content-Type used to communicate with the token endpoint. Default value (application/x-www-form-urlencoded).
     * @param contentType The Content-Type
     */
    public void setContentType(@Nonnull MediaType contentType) {
        this.contentType = contentType;
    }
}
