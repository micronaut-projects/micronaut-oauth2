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

package io.micronaut.security.oauth2.configuration;

import io.micronaut.context.annotation.ConfigurationProperties;
import io.micronaut.context.annotation.Requires;
import io.micronaut.security.config.SecurityConfigurationProperties;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

/**
 * {@link io.micronaut.context.annotation.ConfigurationProperties} implementation of {@link io.micronaut.security.oauth2.configuration}.
 *
 * @author Sergio del Amo
 * @since 1.1.0
 */
@Requires(property = OauthConfigurationProperties.PREFFIX + ".client-id")
@ConfigurationProperties(OauthConfigurationProperties.PREFFIX)
public class OauthConfigurationProperties implements OauthConfiguration {
    public static final String PREFFIX = SecurityConfigurationProperties.PREFIX + ".oauth2";

    @NotNull
    private String clientId;

    @Nullable
    private String clientSecret;

    /**
     * Oauth 2 Application Client ID.
     * @param clientId The application's Client ID.
     */
    public void setClientId(@NotNull String clientId) {
        this.clientId = clientId;
    }

    /**
     * Oauth 2 Application Client Secret.
     * @param clientSecret The application's Client Secret.
     */
    public void setClientSecret(@Nullable String clientSecret) {
        this.clientSecret = clientSecret;
    }

    /**
     *
     * @return the application's Client identifier
     */
    @NotNull
    @Override
    public String getClientId() {
        return clientId;
    }

    /**
     *
     @return the application's Client secret
     */
    @Nullable
    @Override
    public String getClientSecret() {
        return clientSecret;
    }

}
