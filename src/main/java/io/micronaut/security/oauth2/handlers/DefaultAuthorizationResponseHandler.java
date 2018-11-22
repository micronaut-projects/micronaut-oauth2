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

import io.micronaut.context.annotation.Requires;
import io.micronaut.context.annotation.Secondary;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.security.oauth2.grants.AuthorizationCodeGrant;
import io.micronaut.security.oauth2.openid.endpoints.token.AuthorizationCodeGrantRequestGenerator;
import io.micronaut.security.oauth2.responses.AuthorizationResponse;
import io.reactivex.Flowable;
import io.reactivex.Single;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import java.net.MalformedURLException;
import java.util.Map;
import java.util.Optional;

/**
 * Default implementation of {@link AuthorizationResponseHandler}.
 *
 * @author Sergio del Amo
 * @since 1.1.0
 */
@Secondary
@Singleton
@Requires(beans = {AuthorizationCodeGrantRequestGenerator.class})
public class DefaultAuthorizationResponseHandler implements AuthorizationResponseHandler {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultAuthorizationResponseHandler.class);

    private final AuthorizationCodeGrantRequestGenerator authorizationCodeGrantRequestGenerator;

    /**
     * Creates a DefaultAuthorizationResponseHandler.
     * @param authorizationCodeGrantRequestGenerator Authorization Code Grant Request Generator
     */
    public DefaultAuthorizationResponseHandler(AuthorizationCodeGrantRequestGenerator authorizationCodeGrantRequestGenerator) {
        this.authorizationCodeGrantRequestGenerator = authorizationCodeGrantRequestGenerator;
    }

    @Override
    public Single<HttpResponse> handle(AuthorizationResponse authorizationResponse) {

        HttpRequest<AuthorizationCodeGrant> request = authorizationCodeGrantRequestGenerator.generateRequest(authorizationResponse.getCode());
        try {
            RxHttpClient rxHttpClient = RxHttpClient.create(request.getUri().toURL());
            Flowable<HttpResponse<Map>> flowable = rxHttpClient.exchange(request, Map.class);
            return flowable.map(response -> {
                Optional<Map> mapOptional = response.getBody();
                HttpResponse rsp = HttpResponse.ok();
                return rsp;
            }).firstOrError();


        } catch (MalformedURLException e) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("uri: {}", request.getUri(), e);
            }
        }

        return Single.just(HttpResponse.serverError()); //TODO remove this
    }
}
