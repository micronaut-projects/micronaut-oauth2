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

package io.micronaut.security.oauth2.endpoints;

import io.micronaut.context.annotation.Requires;
import io.micronaut.core.util.StringUtils;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.oauth2.openid.endpoints.authorization.AuthorizationRequestResponseTypeCodeCondition;
import io.micronaut.security.oauth2.openid.endpoints.token.TokenEndpointGrantTypeAuthorizationCodeCondition;
import io.micronaut.security.rules.SecurityRule;
import io.reactivex.Single;

/**
 * Callback controller used for Authorization code flow.
 *
 * @author Sergio del Amo
 * @since 1.1.0
 */
@Secured(SecurityRule.IS_ANONYMOUS)
@Requires(property = AuthorizationCodeControllerConfigurationProperties.PREFIX + ".enabled", notEquals = StringUtils.FALSE)
@Requires(condition = AuthorizationRequestResponseTypeCodeCondition.class)
@Requires(condition = TokenEndpointGrantTypeAuthorizationCodeCondition.class)
@Controller("${" + AuthorizationCodeControllerConfigurationProperties.PREFIX + ".controller-path:/authcode}")
public class AuthorizationCodeController {

    /**
     * Callback action accessible through an Http Post request.
     * @return An HttpResponse.
     */
    @Post("${" + AuthorizationCodeControllerConfigurationProperties.PREFIX + ".action-path:/cb}")
    public Single<HttpResponse> cbPost() {
        return Single.just(HttpResponse.ok());
    }

    /**
     * Callback action accessible through an Http Get request.
     * @return An HttpResponse.
     */
    @Get("${" + AuthorizationCodeControllerConfigurationProperties.PREFIX + ".action-path:/cb}")
    public Single<HttpResponse> cbGet() {
        return Single.just(HttpResponse.ok());
    }
}
