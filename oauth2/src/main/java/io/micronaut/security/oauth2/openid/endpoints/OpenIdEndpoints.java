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

package io.micronaut.security.oauth2.openid.endpoints;

import javax.annotation.Nullable;

/**
 * Open ID Connect endpoint urls.
 *
 * @author Sergio del Amo
 * @since 1.0.0
 */
public interface OpenIdEndpoints {

    /**
     *
     * @return Authorization endpoint url.
     */
    String getAuthorization();

    /**
     *
     * @return end-session endpoint url.
     */
    @Nullable
    String getEndSession();

    /**
     *
     * @return introspection endpoint url.
     */
    String getIntrospection();

    /**
     *
     * @return registration endpoint url.
     */
    String getRegistration();

    /**
     *
     * @return revocation endpoint url.
     */
    String getRevocation();

    /**
     *
     * @return token endpoint url.
     */
    String getToken();

    /**
     *
     * @return user info endpoint url.
     */
    String getUserinfo();
}
