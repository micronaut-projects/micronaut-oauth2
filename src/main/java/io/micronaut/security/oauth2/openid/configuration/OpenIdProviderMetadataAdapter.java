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

package io.micronaut.security.oauth2.openid.configuration;

import io.micronaut.security.oauth2.openid.endpoints.EndpointUrl;
import io.micronaut.security.oauth2.openid.endpoints.authorization.AuthorizationEndpointConfiguration;
import io.micronaut.security.oauth2.openid.endpoints.introspection.IntrospectionEndpointConfiguration;
import io.micronaut.security.oauth2.openid.endpoints.registration.RegistrationEndpointConfiguration;
import io.micronaut.security.oauth2.openid.endpoints.revocation.RevocationEndpointConfiguration;
import io.micronaut.security.oauth2.openid.endpoints.token.TokenEndpointConfiguration;
import io.micronaut.security.oauth2.openid.endpoints.userinfo.UserInfoEndpointConfiguration;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

/**
 * Creates an {@link OpenIdProviderMetadata} by merging together an existing {@link OpenIdProviderMetadata}, probably from a
 * fetched from remote identity provider, with the different endpoint configurations.
 *
 * @author Sergio del Amo
 * @since 1.1.0
 */
public class OpenIdProviderMetadataAdapter implements OpenIdProviderMetadata {

    @Nonnull
    private OpenIdProviderMetadata openIdProviderMetadata;

    @Nonnull
    private AuthorizationEndpointConfiguration authorizationEndpointConfiguration;

    @Nonnull
    private IntrospectionEndpointConfiguration introspectionEndpointConfiguration;

    @Nonnull
    private RegistrationEndpointConfiguration registrationEndpointConfiguration;

    @Nonnull
    private RevocationEndpointConfiguration revocationEndpointConfiguration;

    @Nonnull
    private TokenEndpointConfiguration tokenEndpointConfiguration;

    @Nonnull
    private UserInfoEndpointConfiguration userInfoEndpointConfiguration;

    /**
     *
     * @param openIdProviderMetadata Open ID provider metadata
     * @param authorizationEndpointConfiguration Authorization endpoint configuration.
     * @param introspectionEndpointConfiguration Introspection endpoint configuration.
     * @param registrationEndpointConfiguration Registration endpoint configuration.
     * @param revocationEndpointConfiguration Revocation endpoint configuration.
     * @param tokenEndpointConfiguration Token endpoint configuration.
     * @param userInfoEndpointConfiguration User info endpoint configuration.
     */
    public OpenIdProviderMetadataAdapter(@Nonnull OpenIdProviderMetadata openIdProviderMetadata,
                                         @Nonnull AuthorizationEndpointConfiguration authorizationEndpointConfiguration,
                                         @Nonnull IntrospectionEndpointConfiguration introspectionEndpointConfiguration,
                                         @Nonnull RegistrationEndpointConfiguration registrationEndpointConfiguration,
                                         @Nonnull RevocationEndpointConfiguration revocationEndpointConfiguration,
                                         @Nonnull TokenEndpointConfiguration tokenEndpointConfiguration,
                                         @Nonnull UserInfoEndpointConfiguration userInfoEndpointConfiguration) {
        this.openIdProviderMetadata = openIdProviderMetadata;
        this.authorizationEndpointConfiguration = authorizationEndpointConfiguration;
        this.introspectionEndpointConfiguration = introspectionEndpointConfiguration;
        this.registrationEndpointConfiguration = registrationEndpointConfiguration;
        this.revocationEndpointConfiguration = revocationEndpointConfiguration;
        this.tokenEndpointConfiguration = tokenEndpointConfiguration;
        this.userInfoEndpointConfiguration = userInfoEndpointConfiguration;
    }

    @Nonnull
    @Override
    public String getIssuer() {
        return openIdProviderMetadata.getIssuer();
    }

    @Nonnull
    @Override
    public String getAuthorizationEndpoint() {
        return getAuthorizationEndpointUrl();
    }

    @Nullable
    @Override
    public String getTokenEndpoint() {
        return getTokenEndpointUrl();
    }

    @Nullable
    @Override
    public String getUserinfoEndpoint() {
        return getUserinfoEndpointUrl();
    }

    @Nonnull
    @Override
    public String getJwksUri() {
        return openIdProviderMetadata.getJwksUri();
    }

    @Nullable
    @Override
    public String getRegistrationEndpoint() {
        return getRegistrationEndpointUrl();
    }

    @Nullable
    @Override
    public List<String> getScopesSupported() {
        return openIdProviderMetadata.getScopesSupported();
    }

    @Nullable
    @Override
    public List<String> getResponseTypesSupported() {
        return openIdProviderMetadata.getResponseTypesSupported();
    }

    @Nullable
    @Override
    public List<String> getResponseModesSupported() {
        return openIdProviderMetadata.getResponseModesSupported();
    }

    @Nullable
    @Override
    public List<String> getGrantTypesSupported() {
        return openIdProviderMetadata.getGrantTypesSupported();
    }

    @Nullable
    @Override
    public List<String> getAcrValuesSupported() {
        return openIdProviderMetadata.getAcrValuesSupported();
    }

    @Nonnull
    @Override
    public List<String> getSubjectTypesSupported() {
        return openIdProviderMetadata.getSubjectTypesSupported();
    }

    @Nonnull
    @Override
    public List<String> getIdTokenSigningAlgValuesSupported() {
        return openIdProviderMetadata.getIdTokenSigningAlgValuesSupported();
    }

    @Nullable
    @Override
    public List<String> getIdTokenEncryptionEncValuesSupported() {
        return openIdProviderMetadata.getIdTokenEncryptionEncValuesSupported();
    }

    @Nullable
    @Override
    public List<String> getUserInfoEncryptionAlgValuesSupported() {
        return openIdProviderMetadata.getUserInfoEncryptionAlgValuesSupported();
    }

    @Nullable
    @Override
    public List<String> getUserinfoEncryptionEncValuesSupported() {
        return openIdProviderMetadata.getUserinfoEncryptionEncValuesSupported();
    }

    @Nullable
    @Override
    public List<String> getRequestObjectSigningAlgValuesSupported() {
        return openIdProviderMetadata.getRequestObjectSigningAlgValuesSupported();
    }

    @Nullable
    @Override
    public List<String> getRequestObjectEncryptionAlgValuesSupported() {
        return openIdProviderMetadata.getRequestObjectEncryptionAlgValuesSupported();
    }

    @Nullable
    @Override
    public List<String> getRequestObjectEncryptionEncValuesSupported() {
        return openIdProviderMetadata.getRequestObjectEncryptionEncValuesSupported();
    }

    @Nullable
    @Override
    public List<String> getTokenEndpointAuthMethodsSupported() {
        return openIdProviderMetadata.getTokenEndpointAuthMethodsSupported();
    }

    @Nullable
    @Override
    public List<String> getTokenEndpointAuthSigningAlgValuesSupported() {
        return openIdProviderMetadata.getTokenEndpointAuthSigningAlgValuesSupported();
    }

    @Nullable
    @Override
    public List<String> getDisplayValuesSupported() {
        return openIdProviderMetadata.getDisplayValuesSupported();
    }

    @Nullable
    @Override
    public List<String> getClaimTypesSupported() {
        return openIdProviderMetadata.getClaimTypesSupported();
    }

    @Nullable
    @Override
    public List<String> getClaimsSupported() {
        return openIdProviderMetadata.getClaimsSupported();
    }

    @Nullable
    @Override
    public String getServiceDocumentation() {
        return openIdProviderMetadata.getServiceDocumentation();
    }

    @Nullable
    @Override
    public List<String> getClaimsLocalesSupported() {
        return openIdProviderMetadata.getClaimsLocalesSupported();
    }

    @Nullable
    @Override
    public List<String> getUriLocalesSupported() {
        return openIdProviderMetadata.getUriLocalesSupported();
    }

    @Nullable
    @Override
    public Boolean getClaimsParameterSupported() {
        return openIdProviderMetadata.getClaimsParameterSupported();
    }

    @Nullable
    @Override
    public Boolean getRequestParameterSupported() {
        return openIdProviderMetadata.getRequestParameterSupported();
    }

    @Nullable
    @Override
    public Boolean getRequestUriParameterSupported() {
        return openIdProviderMetadata.getRequestUriParameterSupported();
    }

    @Nullable
    @Override
    public Boolean getRequireRequestUriRegistration() {
        return openIdProviderMetadata.getRequireRequestUriRegistration();
    }

    @Nullable
    @Override
    public String getOpPolicyUri() {
        return openIdProviderMetadata.getOpPolicyUri();
    }

    @Nullable
    @Override
    public String getOpTosUri() {
        return openIdProviderMetadata.getOpTosUri();
    }

    @Nullable
    @Override
    public List<String> getCodeChallengeMethodsSupported() {
        return openIdProviderMetadata.getCodeChallengeMethodsSupported();
    }

    @Nullable
    @Override
    public List<String> getIntrospectionEndpointAuthMethodsSupported() {
        return openIdProviderMetadata.getIntrospectionEndpointAuthMethodsSupported();
    }

    @Nullable
    @Override
    public List<String> getRevocationEndpointAuthMethodsSupported() {
        return openIdProviderMetadata.getRevocationEndpointAuthMethodsSupported();
    }

    @Nullable
    @Override
    public String getIntrospectionEndpoint() {
        return openIdProviderMetadata.getIntrospectionEndpoint();
    }

    @Nullable
    @Override
    public String getRevocationEndpoint() {
        return openIdProviderMetadata.getRevocationEndpoint();
    }

    /**
     *
     * @return resolved userinfo endpoint url
     */
    protected String getUserinfoEndpointUrl() {
        return resolveUrl(userInfoEndpointConfiguration, openIdProviderMetadata.getUserinfoEndpoint());
    }

    /**
     *
     * @return resolved token endpoint url
     */
    protected String getTokenEndpointUrl() {
        return resolveUrl(tokenEndpointConfiguration, openIdProviderMetadata.getTokenEndpoint());
    }

    /**
     *
     * @return resolved revocation endpoint url
     */
    protected String getRevocationEndpointUrl() {
        return resolveUrl(revocationEndpointConfiguration, openIdProviderMetadata.getRevocationEndpoint());
    }

    /**
     *
     * @return resolved registration endpoint url
     */
    protected String getRegistrationEndpointUrl() {
        return resolveUrl(registrationEndpointConfiguration, openIdProviderMetadata.getRegistrationEndpoint());
    }

    /**
     *
     * @return resolved introspection endpoint url
     */
    protected String getIntrospectionEndpointUrl() {
        return resolveUrl(introspectionEndpointConfiguration, openIdProviderMetadata.getIntrospectionEndpoint());
    }

    /**
     *
     * @return resolved authorization endpoint url
     */
    protected String getAuthorizationEndpointUrl() {
        return resolveUrl(authorizationEndpointConfiguration, openIdProviderMetadata.getAuthorizationEndpoint());
    }

    private String resolveUrl(EndpointUrl endpointUrl, String url) {
        return endpointUrl.getUrl() != null ? endpointUrl.getUrl() : url;
    }
}
