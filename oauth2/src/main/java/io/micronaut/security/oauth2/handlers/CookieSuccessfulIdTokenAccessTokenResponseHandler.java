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

package io.micronaut.security.oauth2.handlers;

import io.micronaut.context.annotation.Requires;
import io.micronaut.core.util.StringUtils;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.cookie.Cookie;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.oauth2.openid.endpoints.authorization.state.State;
import io.micronaut.security.oauth2.openid.idtoken.IdTokenAccessTokenResponse;
import io.micronaut.security.oauth2.responses.AuthenticationResponse;
import io.micronaut.security.token.jwt.generator.claims.JwtClaims;

import javax.inject.Singleton;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Default implementation of {@link SuccessfulIdTokenAccessTokenResponseHandler}.
 *
 * @author Sergio del Amo
 * @since 1.0.0
 */
@Requires(property = CookieSuccessfulIdTokenAccessTokenResponseHandlerConfigurationProperties.PREFIX + ".enabled", notEquals = StringUtils.FALSE)
@Requires(beans = { CookieSuccessfulIdTokenAccessTokenResponseHandlerConfiguration.class })
@Singleton
public class CookieSuccessfulIdTokenAccessTokenResponseHandler implements SuccessfulIdTokenAccessTokenResponseHandler {

    private final CookieSuccessfulIdTokenAccessTokenResponseHandlerConfiguration configuration;

    /**
     *
     * @param configuration Cookie Successful IdToken-AccessToken Handler
     */
    public CookieSuccessfulIdTokenAccessTokenResponseHandler(
            CookieSuccessfulIdTokenAccessTokenResponseHandlerConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public HttpResponse handle(HttpRequest request,
                               AuthenticationResponse authenticationResponse,
                               IdTokenAccessTokenResponse idTokenAccessTokenResponse,
                               Authentication authentication) {
        Cookie cookie = Cookie.of(configuration.getCookieName(), idTokenAccessTokenResponse.getIdToken());
        cookie.configure(configuration, request.isSecure());
        if (!configuration.getCookieMaxAge().isPresent()) {
            long seconds = secondsToExpirationTime(authentication);
            cookie.maxAge(seconds);
        }

        URI location = getRedirectUri(request, authenticationResponse, idTokenAccessTokenResponse, authentication);
        return HttpResponse.seeOther(location).cookie(cookie);
    }

    /**
     *
     * @param authentication Authentication
     * @return Number of seconds to JWT expiration time.
     */
    protected long secondsToExpirationTime(Authentication authentication) {
        Object expDate = authentication.getAttributes().get(JwtClaims.EXPIRATION_TIME);
        if (expDate instanceof Date) {
            Date now = new Date();
            Date expirationDate = (Date) expDate;
            long diffInMillies = Math.abs(expirationDate.getTime() - now.getTime());
            return TimeUnit.SECONDS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        }

        return Integer.MAX_VALUE;
    }

    /**
     * @param request The HTTP request
     * @param authenticationResponse The authentication response
     * @param idTokenAccessTokenResponse The ID Token Access Token response.
     * @param authentication The authentication
     * @return The URI to redirect to
     */
    protected URI getRedirectUri(HttpRequest request,
                                 AuthenticationResponse authenticationResponse,
                                 IdTokenAccessTokenResponse idTokenAccessTokenResponse,
                                 Authentication authentication) {

        URI uri = configuration.getDefaultRedirectUri().orElse(null);
        if (configuration.getRedirectionStrategy() == RedirectionStrategy.ORIGINAL) {
            State state = authenticationResponse.getState();
            if (state != null) {
                uri = state.getOriginalUri();
            }
        }
        if (uri == null) {
            try {
                return new URI("/");
            } catch (URISyntaxException e) {
                throw new RuntimeException("This should never happen", e);
            }
        } else {
            return uri;
        }
    }
}
