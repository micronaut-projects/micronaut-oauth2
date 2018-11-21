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

package io.micronaut.security.oauth2.openid.endpoints.registration;

import io.micronaut.security.oauth2.openid.endpoints.EndpointUrl;

/**
 * Registration endpoint configuration.
 *
 * @see <a href="https://tools.ietf.org/html/draft-ietf-oauth-dyn-reg-17#section-3">Client Registration Endpoint</a>
 *
 * @since 1.1.0
 * @author Sergio del Amo
 */
public interface RegistrationEndpointConfiguration extends EndpointUrl {
}