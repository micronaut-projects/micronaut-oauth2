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

package io.micronaut.security.oauth2.openid.configuration;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.security.oauth2.configuration.OauthConfigurationProperties;

/**
 * Creates a HTTP Declarative client to communicate with an OpenID connect Discovery endpoint.
 * The discovery endpoint is declared by the property micronaut.security.oauth2.issuer
 *
 * @author Sergio del Amo
 * @since 1.0.0
 */
@Requires(property = OauthConfigurationProperties.PREFIX + ".issuer")
@Client("${micronaut.security.oauth2.issuer}")
public interface OpenIdConfigurationClient {

    /**
     *
     * @return the OpenID Provider Metadata
     */
    @Get("${micronaut.security.oauth2.openid-configuration-path:/.well-known/openid-configuration}")
    OpenIdConfiguration fetchConfiguration();
}