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

package io.micronaut.security.oauth2.handlers;

import io.micronaut.core.util.Toggleable;
import javax.annotation.Nonnull;
import java.time.temporal.TemporalAmount;
import java.util.Optional;

public interface CookieSuccessfulIdTokenAccessTokenResponseHandlerConfiguration extends Toggleable {

    /**
     *
     * @return String to be parsed into a URI which represents where the user is redirected to after a successful login.
     */
    String getLoginSuccessTargetUrl();

    // TODO remove this and use CookieConfiguration
    // https://github.com/micronaut-projects/micronaut-core/pull/1185
    /**
     * @return The name of the cookie
     */
    @Nonnull
    String getCookieName();

    /**
     * Gets the domain name of this Cookie.
     *
     * @return the domain name of this Cookie
     */
    Optional<String> getCookieDomain();

    /**
     * The path of the cookie. The cookie is visible to all paths below the request path on the server.
     *
     * @return The cookie path
     */
    Optional<String> getCookiePath();

    /**
     * Checks to see if this Cookie can only be accessed via HTTP.
     */
    Optional<Boolean> isCookieHttpOnly();

    /**
     * @return True if the cookie is secure
     */
    Optional<Boolean> isCookieSecure();

    /**
     * @return The max age to use for the cookie
     */
    Optional<TemporalAmount> getCookieMaxAge();
}
