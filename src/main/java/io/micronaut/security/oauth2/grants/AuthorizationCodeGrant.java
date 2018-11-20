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

package io.micronaut.security.oauth2.grants;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Nonnull;

/**
 * Authorization Code Grant Request.
 * @see <a href="https://tools.ietf.org/html/rfc6749#section-4.1.3">Access Token Request</a>
 *
 * @author Sergio del Amo
 * @since 1.1.0
 */
public class AuthorizationCodeGrant {
    @Nonnull
    @JsonProperty("grant_type")
    private String grantType = GrantType.AUTHORIZATION_CODE.getGrantType();

    @Nonnull
    @JsonProperty("client_id")
    private String clientId;

    @Nonnull
    @JsonProperty("redirect_uri")
    private String redirectUri;

    @Nonnull
    private String code;

    /**
     * Instantiate Authorization Grant.
     */
    public AuthorizationCodeGrant() {

    }

    /**
     *
     * @return Oauth 2.0 Grant Type.
     */
    @Nonnull
    public String getGrantType() {
        return grantType;
    }

    /**
     *
     * @param grantType Oauth 2.0 Grant Type.
     */
    public void setGrantType(@Nonnull String grantType) {
        this.grantType = grantType;
    }

    /**
     *
     * @return The application's Client identifier.
     */
    @Nonnull
    public String getClientId() {
        return clientId;
    }

    /**
     *
     * @param clientId Application's Client identifier.
     */
    public void setClientId(@Nonnull String clientId) {
        this.clientId = clientId;
    }

    /**
     *
     * @return Redirection URI to which the response will be sent.
     */
    @Nonnull
    public String getRedirectUri() {
        return redirectUri;
    }

    /**
     *
     * @param redirectUri Redirection URI to which the response will be sent.
     */
    public void setRedirectUri(@Nonnull String redirectUri) {
        this.redirectUri = redirectUri;
    }

    /**
     *
     * @return An authorization code.
     */
    @Nonnull
    public String getCode() {
        return code;
    }

    /**
     *
     * @param code An authorization code.
     */
    public void setCode(@Nonnull String code) {
        this.code = code;
    }
}
