package io.micronaut.security.oauth2

import io.micronaut.security.oauth2.openid.configuration.OpenIdProviderMetadata

class NullImplOfOpenIdProviderMetadata implements OpenIdProviderMetadata {
    @Override
    String getIssuer() {
        return null
    }

    @Override
    String getAuthorizationEndpoint() {
        return null
    }

    @Override
    String getTokenEndpoint() {
        return null
    }

    @Override
    String getUserinfoEndpoint() {
        return null
    }

    @Override
    String getJwksUri() {
        return null
    }

    @Override
    String getRegistrationEndpoint() {
        return null
    }

    @Override
    List<String> getScopesSupported() {
        return null
    }

    @Override
    List<String> getResponseTypesSupported() {
        return null
    }

    @Override
    List<String> getResponseModesSupported() {
        return null
    }

    @Override
    List<String> getGrantTypesSupported() {
        return null
    }

    @Override
    List<String> getAcrValuesSupported() {
        return null
    }

    @Override
    List<String> getSubjectTypesSupported() {
        return null
    }

    @Override
    List<String> getIdTokenSigningAlgValuesSupported() {
        return null
    }

    @Override
    List<String> getIdTokenEncryptionEncValuesSupported() {
        return null
    }

    @Override
    List<String> getUserInfoEncryptionAlgValuesSupported() {
        return null
    }

    @Override
    List<String> getUserinfoEncryptionEncValuesSupported() {
        return null
    }

    @Override
    List<String> getRequestObjectSigningAlgValuesSupported() {
        return null
    }

    @Override
    List<String> getRequestObjectEncryptionAlgValuesSupported() {
        return null
    }

    @Override
    List<String> getRequestObjectEncryptionEncValuesSupported() {
        return null
    }

    @Override
    List<String> getTokenEndpointAuthMethodsSupported() {
        return null
    }

    @Override
    List<String> getTokenEndpointAuthSigningAlgValuesSupported() {
        return null
    }

    @Override
    List<String> getDisplayValuesSupported() {
        return null
    }

    @Override
    List<String> getClaimTypesSupported() {
        return null
    }

    @Override
    List<String> getClaimsSupported() {
        return null
    }

    @Override
    String getServiceDocumentation() {
        return null
    }

    @Override
    List<String> getClaimsLocalesSupported() {
        return null
    }

    @Override
    List<String> getUriLocalesSupported() {
        return null
    }

    @Override
    Boolean getClaimsParameterSupported() {
        return null
    }

    @Override
    Boolean getRequestParameterSupported() {
        return null
    }

    @Override
    Boolean getRequestUriParameterSupported() {
        return null
    }

    @Override
    Boolean getRequireRequestUriRegistration() {
        return null
    }

    @Override
    String getOpPolicyUri() {
        return null
    }

    @Override
    String getOpTosUri() {
        return null
    }

    @Override
    List<String> getCodeChallengeMethodsSupported() {
        return null
    }

    @Override
    List<String> getIntrospectionEndpointAuthMethodsSupported() {
        return null
    }

    @Override
    List<String> getRevocationEndpointAuthMethodsSupported() {
        return null
    }

    @Override
    String getIntrospectionEndpoint() {
        return null
    }

    @Override
    String getRevocationEndpoint() {
        return null
    }
}
