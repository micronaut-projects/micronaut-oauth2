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

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;

/**
 * @see <a href="https://openid.net/specs/openid-connect-discovery-1_0.html">OpenID connect Discovery Spec</a>
 *
 * @author Sergio del Amo
 * @since 1.0.0
 */
public class OpenIdConfiguration implements OpenIdProviderMetadata, OpenIdProviderMetadataSession {

    @Nonnull
    @JsonProperty("authorization_endpoint")
    private String authorizationEndpoint;

    @Nonnull
    @JsonProperty("id_token_signing_alg_values_supported")
    private List<String> idTokenSigningAlgValuesSupported;

    @Nonnull
    private String issuer;

    @Nonnull
    @JsonProperty("jwks_uri")
    private String jwksUri;

    @Nullable
    @JsonProperty("acr_values_supported")
    private List<String> acrValuesSupported;

    @Nullable
    @JsonProperty("response_types_supported")
    private List<String> responseTypesSupported;

    @Nullable
    @JsonProperty("response_modes_supported")
    private List<String> responseModesSupported;

    @Nullable
    @JsonProperty("scopes_supported")
    private List<String> scopesSupported;

    @Nullable
    @JsonProperty("grant_types_supported")
    private List<String> grantTypesSupported;

    @Nonnull
    @JsonProperty("subject_types_supported")
    private List<String> subjectTypesSupported;

    @Nullable
    @JsonProperty("token_endpoint")
    private String tokenEndpoint;

    @Nullable
    @JsonProperty("token_endpoint_auth_methods_supported")
    private List<String> tokenEndpointAuthMethodsSupported;

    @Nullable
    @JsonProperty("userinfo_endpoint")
    private String userinfoEndpoint;

    @Nullable
    @JsonProperty("registration_endpoint")
    private String registrationEndpoint;

    @Nullable
    @JsonProperty("claims_supported")
    private List<String> claimsSupported;

    @Nullable
    @JsonProperty("code_challenge_methods_supported")
    private List<String> codeChallengeMethodsSupported;

    @Nullable
    @JsonProperty("introspection_endpoint")
    private String introspectionEndpoint;

    @Nullable
    @JsonProperty("introspection_endpoint_auth_methods_supported")
    private List<String> introspectionEndpointAuthMethodsSupported;

    @Nullable
    @JsonProperty("revocation_endpoint")
    private String revocationEndpoint;

    @Nullable
    @JsonProperty("revocation_endpoint_auth_methods_supported")
    private List<String> revocationEndpointAuthMethodsSupported;

    @Nullable
    @JsonProperty("end_session_endpoint")
    private String endSessionEndpoint;

    @Nullable
    @JsonProperty("request_parameter_supported")
    private Boolean requestParameterSupported;

    @Nullable
    @JsonProperty("request_uri_parameter_supported")
    private Boolean requestUriParameterSupported;

    @Nullable
    @JsonProperty("require_request_uri_registration")
    private Boolean requireRequestUriRegistration;

    @Nullable
    @JsonProperty("request_object_signing_alg_values_supported")
    private List<String> requestObjectSigningAlgValuesSupported;

    @Nullable
    @JsonProperty("service_documentation")
    private String serviceDocumentation;

    @Nullable
    @JsonProperty("id_token_encryption_enc_values_supported")
    private List<String> idTokenEncryptionEncValuesSupported;

    @Nullable
    @JsonProperty("display_values_supported")
    private List<String> displayValuesSupported;

    @Nullable
    @JsonProperty("claim_types_supported")
    private List<String> claimTypesSupported;

    @Nullable
    @JsonProperty("claims_parameter_supported")
    private Boolean claimsParameterSupported = Boolean.FALSE;

    @Nullable
    @JsonProperty("op_tos_uri")
    private String opTosUri;

    @Nullable
    @JsonProperty("op_policy_uri")
    private String opPolicyUri;

    @Nullable
    @JsonProperty("uri_locales_supported")
    private List<String> uriLocalesSupported;

    @Nullable
    @JsonProperty("claims_locales_supported")
    private List<String> claimsLocalesSupported;

    @JsonProperty("userinfo_encryption_alg_values_supported")
    @Nullable
    private List<String> userinfoEncryptionAlgValuesSupported;

    @Nullable
    @JsonProperty("userinfo_encryption_enc_values_supported")
    private List<String> userinfoEncryptionEncValuesSupported;

    @Nullable
    @JsonProperty("token_endpoint_auth_signing_alg_values_supported")
    private List<String> tokenEndpointAuthSigningAlgValuesSupported;

    @Nullable
    @JsonProperty("request_object_encryption_alg_values_supported")
    private List<String> requestObjectEncryptionAlgValuesSupported;

    @Nullable
    @JsonProperty("request_object_encryption_enc_values_supported")
    private List<String> requestObjectEncryptionEncValuesSupported;

    @Nullable
    @JsonProperty("check_session_if_frame")
    private String checkSessionIframe;

    /**
     * Empty Constructor.
     */
    public OpenIdConfiguration() {
    }

    /**
     *
     * @return Boolean value specifying whether the OP requires any request_uri values used to be pre-registered using the request_uris registration parameter.
     */
    @Nullable
    public Boolean getRequireRequestUriRegistration() {
        return requireRequestUriRegistration;
    }

    /**
     *
     * @return If require_request_uri_registration omitted, the default value is false.
     */
    @Nullable
    public Boolean getDefaultRequireRequestUriRegistration() {
        return Boolean.FALSE;
    }

    /**
     *
     * @param requireRequestUriRegistration Boolean value specifying whether the OP requires any request_uri values used to be pre-registered using the request_uris registration parameter.
     */
    public void setRequireRequestUriRegistration(@Nullable Boolean requireRequestUriRegistration) {
        this.requireRequestUriRegistration = requireRequestUriRegistration;
    }

    @Nonnull
    @Override
    public String getAuthorizationEndpoint() {
        return authorizationEndpoint;
    }

    /**
     *
     * @param authorizationEndpoint URL of the Open ID Provider's OAuth 2.0 Authorization Endpoint.
     */
    public void setAuthorizationEndpoint(@Nonnull String authorizationEndpoint) {
        this.authorizationEndpoint = authorizationEndpoint;
    }

    /**
     *
     * @param userinfoEncryptionEncValuesSupported List of the JWE encryption algorithms (enc values) [JWA] supported by the UserInfo Endpoint to encode the Claims in a JWT.
     */
    public void setUserinfoEncryptionEncValuesSupported(@Nullable List<String> userinfoEncryptionEncValuesSupported) {
        this.userinfoEncryptionEncValuesSupported = userinfoEncryptionEncValuesSupported;
    }

    @Nonnull
    @Override
    public List<String> getIdTokenSigningAlgValuesSupported() {
        return idTokenSigningAlgValuesSupported;
    }

    @Nullable
    @Override
    public List<String> getIdTokenEncryptionEncValuesSupported() {
        return idTokenEncryptionEncValuesSupported;
    }

    /**
     *
     * @param idTokenEncryptionEncValuesSupported List of the JWE encryption algorithms (enc values) supported by the OP for the ID Token to encode the Claims in a JWT.
     */
    public void setIdTokenEncryptionEncValuesSupported(@Nullable List<String> idTokenEncryptionEncValuesSupported) {
        this.idTokenEncryptionEncValuesSupported = idTokenEncryptionEncValuesSupported;
    }

    @Nullable
    @Override
    public List<String> getUserInfoEncryptionAlgValuesSupported() {
        return userinfoEncryptionAlgValuesSupported;
    }

    /**
     *
     * @param userinfoEncryptionAlgValuesSupported List of the JWE [JWE] encryption algorithms (alg values) [JWA] supported by the UserInfo Endpoint to encode the Claims in a JWT.
     */
    public void setUserinfoEncryptionAlgValuesSupported(@Nullable List<String> userinfoEncryptionAlgValuesSupported) {
        this.userinfoEncryptionAlgValuesSupported = userinfoEncryptionAlgValuesSupported;
    }

    @Nullable
    @Override
    public List<String> getUserinfoEncryptionEncValuesSupported() {
        return userinfoEncryptionEncValuesSupported;
    }

    /**
     *
     * @param idTokenSigningAlgValuesSupported List of the JWE encryption algorithms (enc values) supported by the OP for the ID Token to encode the Claims in a JWT.
     */
    public void setIdTokenSigningAlgValuesSupported(@Nonnull List<String> idTokenSigningAlgValuesSupported) {
        this.idTokenSigningAlgValuesSupported = idTokenSigningAlgValuesSupported;
    }

    @Nonnull
    @Override
    public String getIssuer() {
        return issuer;
    }

    /**
     *
     * @param issuer URL using the https scheme with no query or fragment component that the Open ID Provider asserts as its Issuer Identifier.
     */
    public void setIssuer(@Nonnull String issuer) {
        this.issuer = issuer;
    }

    @Nonnull
    @Override
    public String getJwksUri() {
        return jwksUri;
    }

    /**
     *
     * @param jwksUri URL of the Open ID Provider's JSON Web Key Set.
     */
    public void setJwksUri(@Nonnull String jwksUri) {
        this.jwksUri = jwksUri;
    }

    /**
     * As specified in Open ID Discovery Spec, if omitted, the
     * default for Dynamic OpenID Providers is ["query", "fragment"].
     * @return Supported response types.
     */
    @Nullable
    @Override
    public List<String> getResponseTypesSupported() {
        return responseTypesSupported;
    }

    /**
     *
     * @return if Response Types Supported is ommited, default for Dynamic OpenID Providers is ["query", "fragment"].
     */
    @Nonnull
    public List<String> getDefaultResponseTypesSupported() {
        return Arrays.asList("query", "fragment");
    }

    /**
     *
     * @param responseTypesSupported List of the OAuth 2.0 response_type values that this Open ID Provider supports.
     */
    public void setResponseTypesSupported(@Nullable List<String> responseTypesSupported) {
        this.responseTypesSupported = responseTypesSupported;
    }

    @Nullable
    @Override
    public List<String> getScopesSupported() {
        return scopesSupported;
    }

    /**
     *
     * @param scopesSupported List of the OAuth 2.0 [RFC6749] scope values that this server supports.
     */
    public void setScopesSupported(@Nullable List<String> scopesSupported) {
        this.scopesSupported = scopesSupported;
    }

    @Nonnull
    @Override
    public List<String> getSubjectTypesSupported() {
        return subjectTypesSupported;
    }

    /**
     *
     * @param subjectTypesSupported List of the Subject Identifier types that this OP supports.
     */
    public void setSubjectTypesSupported(@Nonnull List<String> subjectTypesSupported) {
        this.subjectTypesSupported = subjectTypesSupported;
    }

    @Nullable
    @Override
    public String getTokenEndpoint() {
        return tokenEndpoint;
    }

    /**
     *
     * @param tokenEndpoint URL of the Open ID Provider's OAuth 2.0 Token Endpoint.
     */
    public void setTokenEndpoint(@Nullable String tokenEndpoint) {
        this.tokenEndpoint = tokenEndpoint;
    }

    @Nullable
    @Override
    public List<String> getTokenEndpointAuthMethodsSupported() {
        return tokenEndpointAuthMethodsSupported;
    }

    @Nullable
    @Override
    public List<String> getTokenEndpointAuthSigningAlgValuesSupported() {
        return tokenEndpointAuthSigningAlgValuesSupported;
    }

    /**
     *
     * @param tokenEndpointAuthSigningAlgValuesSupported List of the JWS signing algorithms (alg values) supported by the Token Endpoint.
     */
    public void setTokenEndpointAuthSigningAlgValuesSupported(@Nullable List<String> tokenEndpointAuthSigningAlgValuesSupported) {
        this.tokenEndpointAuthSigningAlgValuesSupported = tokenEndpointAuthSigningAlgValuesSupported;
    }

    @Nullable
    @Override
    public List<String> getDisplayValuesSupported() {
        return displayValuesSupported;
    }

    /**
     *
     * @param displayValuesSupported List of the display parameter values that the OpenID Provider supports.
     */
    public void setDisplayValuesSupported(@Nullable List<String> displayValuesSupported) {
        this.displayValuesSupported = displayValuesSupported;
    }

    @Nullable
    @Override
    public List<String> getClaimTypesSupported() {
        return claimTypesSupported;
    }

    /**
     *
     * @param claimTypesSupported List of the Claim Types that the OpenID Provider supports.
     */
    public void setClaimTypesSupported(@Nullable List<String> claimTypesSupported) {
        this.claimTypesSupported = claimTypesSupported;
    }

    /**
     *
     * @param tokenEndpointAuthMethodsSupported List of Client Authentication methods supported by this Token Endpoint.
     */
    public void setTokenEndpointAuthMethodsSupported(@Nullable List<String> tokenEndpointAuthMethodsSupported) {
        this.tokenEndpointAuthMethodsSupported = tokenEndpointAuthMethodsSupported;
    }

    @Nullable
    @Override
    public String getUserinfoEndpoint() {
        return userinfoEndpoint;
    }

    /**
     *
     * @param userinfoEndpoint URL of the Open ID Provider's UserInfo Endpoint.
     */
    public void setUserinfoEndpoint(@Nullable String userinfoEndpoint) {
        this.userinfoEndpoint = userinfoEndpoint;
    }

    @Nullable
    @Override
    public List<String> getResponseModesSupported() {
        return responseModesSupported;
    }

    /**
     *
     * @param responseModesSupported List of the OAuth 2.0 response_mode values that this Open ID Provider supports.
     */
    public void setResponseModesSupported(@Nullable List<String> responseModesSupported) {
        this.responseModesSupported = responseModesSupported;
    }

    @Nullable
    @Override
    public List<String> getGrantTypesSupported() {
        return grantTypesSupported;
    }

    /**
     * As specified in Open ID Discovery Spec, if omitted,
     * the default value is ["authorization_code", "implicit"].
     * @return Default Grant Types if grantTypesSupported is ommited.
     */
    @Nonnull
    public List<String> getDefaultGrantTypesSupported() {
        return Arrays.asList("authorization_code", "implicit");
    }

    @Nullable
    @Override
    public List<String> getAcrValuesSupported() {
        return acrValuesSupported;
    }

    /**
     *
     * @param acrValuesSupported List of the Authentication Context Class References that this OP supports.
     */
    public void setAcrValuesSupported(@Nullable List<String> acrValuesSupported) {
        this.acrValuesSupported = acrValuesSupported;
    }

    /**
     *
     * @param grantTypesSupported List of the OAuth 2.0 Grant Type values that this Open ID Provider supports.
     */
    public void setGrantTypesSupported(@Nullable List<String> grantTypesSupported) {
        this.grantTypesSupported = grantTypesSupported;
    }

    @Nullable
    @Override
    public String getRegistrationEndpoint() {
        return registrationEndpoint;
    }

    /**
     *
     * @param registrationEndpoint URL of the Open ID Provider's Dynamic Client Registration Endpoint.
     */
    public void setRegistrationEndpoint(@Nullable String registrationEndpoint) {
        this.registrationEndpoint = registrationEndpoint;
    }

    @Nullable
    @Override
    public List<String> getClaimsSupported() {
        return claimsSupported;
    }

    @Nullable
    @Override
    public String getServiceDocumentation() {
        return serviceDocumentation;
    }

    /**
     *
     * @param serviceDocumentation URL of a page containing human-readable information that developers might want or need to know when using the OpenID Provider.
     */
    public void setServiceDocumentation(@Nullable String serviceDocumentation) {
        this.serviceDocumentation = serviceDocumentation;
    }

    @Nullable
    @Override
    public List<String> getClaimsLocalesSupported() {
        return claimsLocalesSupported;
    }

    /**
     *
     * @param claimsLocalesSupported Languages and scripts supported for values in Claims.
     */
    public void setClaimsLocalesSupported(@Nullable List<String> claimsLocalesSupported) {
        this.claimsLocalesSupported = claimsLocalesSupported;
    }

    @Nullable
    @Override
    public List<String> getUriLocalesSupported() {
        return uriLocalesSupported;
    }

    /**
     *
     * @param uriLocalesSupported Languages and scripts supported for the user interface.
     */
    public void setUriLocalesSupported(@Nullable List<String> uriLocalesSupported) {
        this.uriLocalesSupported = uriLocalesSupported;
    }

    @Nullable
    @Override
    public Boolean getClaimsParameterSupported() {
        return claimsParameterSupported;
    }

    /**
     *
     * @param claimsParameterSupported Boolean value specifying whether the OP supports use of the claims parameter.
     */
    public void setClaimsParameterSupported(@Nullable Boolean claimsParameterSupported) {
        this.claimsParameterSupported = claimsParameterSupported;
    }

    /**
     *
     * @param claimsSupported List of the Claim Names of the Claims that the OpenID Provider MAY be able to supply values for.
     */
    public void setClaimsSupported(@Nullable List<String> claimsSupported) {
        this.claimsSupported = claimsSupported;
    }

    @Nullable
    @Override
    public List<String> getCodeChallengeMethodsSupported() {
        return codeChallengeMethodsSupported;
    }

    /**
     *
     * @param codeChallengeMethodsSupported List of the supported transformation methods by the authorisation code verifier for Proof Key for Code Exchange (PKCE).
     */
    public void setCodeChallengeMethodsSupported(@Nullable List<String> codeChallengeMethodsSupported) {
        this.codeChallengeMethodsSupported = codeChallengeMethodsSupported;
    }

    @Nullable
    @Override
    public String getIntrospectionEndpoint() {
        return introspectionEndpoint;
    }

    /**
     *
     * @param introspectionEndpoint The fully qualified URL of the server's introspection endpoint defined by OAuth Token Introspection [RFC7662].
     */
    public void setIntrospectionEndpoint(@Nullable String introspectionEndpoint) {
        this.introspectionEndpoint = introspectionEndpoint;
    }

    @Nullable
    @Override
    public List<String> getIntrospectionEndpointAuthMethodsSupported() {
        return introspectionEndpointAuthMethodsSupported;
    }

    /**
     *
     * @param introspectionEndpointAuthMethodsSupported List of Client Authentication methods supported by Introspection Endpoint.
     */
    public void setIntrospectionEndpointAuthMethodsSupported(@Nullable List<String> introspectionEndpointAuthMethodsSupported) {
        this.introspectionEndpointAuthMethodsSupported = introspectionEndpointAuthMethodsSupported;
    }

    @Nullable
    @Override
    public String getRevocationEndpoint() {
        return revocationEndpoint;
    }

    /**
     *
     * @param revocationEndpoint The fully qualified URL of the server's revocation endpoint defined by Oauth Token Revocation.
     */
    public void setRevocationEndpoint(@Nullable String revocationEndpoint) {
        this.revocationEndpoint = revocationEndpoint;
    }

    @Nullable
    @Override
    public List<String> getRevocationEndpointAuthMethodsSupported() {
        return revocationEndpointAuthMethodsSupported;
    }

    /**
     *
     * @param revocationEndpointAuthMethodsSupported List of Client Authentication methods supported by Revocation Endpoint.
     */
    public void setRevocationEndpointAuthMethodsSupported(@Nullable List<String> revocationEndpointAuthMethodsSupported) {
        this.revocationEndpointAuthMethodsSupported = revocationEndpointAuthMethodsSupported;
    }

    /**
     *
     * @param checkSessionIframe URL of an OP iframe that supports cross-origin communications for session state information with the RP Client, using the HTML5 postMessage API.
     */
    public void setCheckSessionIframe(@Nullable String checkSessionIframe) {
        this.checkSessionIframe = checkSessionIframe;
    }

    @Nullable
    @Override
    public String getCheckSessionIframe() {
        return checkSessionIframe;
    }

    @Nullable
    @Override
    public String getEndSessionEndpoint() {
        return endSessionEndpoint;
    }

    /**
     *
     * @param endSessionEndpoint URL at the OP to which an RP can perform a redirect to request that the End-User be logged out at the OP.
     */
    public void setEndSessionEndpoint(@Nullable String endSessionEndpoint) {
        this.endSessionEndpoint = endSessionEndpoint;
    }

    @Nullable
    @Override
    public Boolean getRequestParameterSupported() {
        return requestParameterSupported;
    }

    /**
     * @return As per spec, If requestParameterSupported omitted, the default value is false.
     */
    @Nonnull
    public Boolean getDefaultRequestParameterSupported() {
        return Boolean.FALSE;
    }

    @Nullable
    @Override
    public Boolean getRequestUriParameterSupported() {
        return requestUriParameterSupported;
    }

    /**
     * @return As per spec, If requestUriParameterSupported omitted, the default value is false.
     */
    @Nonnull
    public Boolean getDefaultRequestUriParameterSupported() {
        return Boolean.TRUE;
    }

    /**
     *
     * @param requestUriParameterSupported  Boolean value specifying whether the OP requires any request_uri values used to be pre-registered using the request_uris registration parameter.
     */
    public void setRequestUriParameterSupported(@Nullable Boolean requestUriParameterSupported) {
        this.requestUriParameterSupported = requestUriParameterSupported;
    }

    @Nullable
    @Override
    public String getOpPolicyUri() {
        return opPolicyUri;
    }

    /**
     *
     * @param opPolicyUri URL that the OpenID Provider provides to the person registering the Client to read about the OP's requirements on how the Relying Party can use the data provided by the OP.
     */
    public void setOpPolicyUri(@Nullable String opPolicyUri) {
        this.opPolicyUri = opPolicyUri;
    }

    @Nullable
    @Override
    public String getOpTosUri() {
        return opTosUri;
    }

    /**
     *
     * @param opTosUri URL that the OpenID Provider provides to the person registering the Client to read about OpenID Provider's terms of service.
     */
    public void setOpTosUri(@Nullable String opTosUri) {
        this.opTosUri = opTosUri;
    }

    /**
     *
     * @param requestParameterSupported Boolean value specifying whether the OP supports use of the request parameter, with true indicating support.
     */
    public void setRequestParameterSupported(@Nullable Boolean requestParameterSupported) {
        this.requestParameterSupported = requestParameterSupported;
    }

    @Nullable
    @Override
    public List<String> getRequestObjectSigningAlgValuesSupported() {
        return requestObjectSigningAlgValuesSupported;
    }

    @Nullable
    @Override
    public List<String> getRequestObjectEncryptionAlgValuesSupported() {
        return requestObjectEncryptionAlgValuesSupported;
    }

    /**
     *
     * @param requestObjectEncryptionAlgValuesSupported List of the JWE encryption algorithms (alg values) supported by the OP for Request Objects.
     */
    public void setRequestObjectEncryptionAlgValuesSupported(@Nullable List<String> requestObjectEncryptionAlgValuesSupported) {
        this.requestObjectEncryptionAlgValuesSupported = requestObjectEncryptionAlgValuesSupported;
    }

    @Nullable
    @Override
    public List<String> getRequestObjectEncryptionEncValuesSupported() {
        return requestObjectEncryptionEncValuesSupported;
    }

    /**
     *
     * @param requestObjectEncryptionEncValuesSupported List of the JWE encryption algorithms (enc values) supported by the OP for Request Objects.
     */
    public void setRequestObjectEncryptionEncValuesSupported(@Nullable List<String> requestObjectEncryptionEncValuesSupported) {
        this.requestObjectEncryptionEncValuesSupported = requestObjectEncryptionEncValuesSupported;
    }

    /**
     *
     * @param requestObjectSigningAlgValuesSupported List of the JWS signing algorithms (alg values) supported by the OP for Request Objects.
     */
    public void setRequestObjectSigningAlgValuesSupported(@Nullable List<String> requestObjectSigningAlgValuesSupported) {
        this.requestObjectSigningAlgValuesSupported = requestObjectSigningAlgValuesSupported;
    }
}
