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
import io.micronaut.http.HttpParameters;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Consumes;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.oauth2.handlers.ErrorResponseHandler;
import io.micronaut.security.oauth2.openid.endpoints.authorization.AuthorizationRequestResponseTypeCodeCondition;
import io.micronaut.security.oauth2.openid.endpoints.token.TokenEndpointGrantTypeAuthorizationCodeCondition;
import io.micronaut.security.oauth2.responses.ErrorResponse;
import io.micronaut.security.oauth2.responses.ErrorResponseHttpParamsAdapter;
import io.micronaut.security.oauth2.responses.ErrorResponseMapAdapter;
import io.micronaut.security.rules.SecurityRule;
import io.reactivex.Single;

import java.util.Map;

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

    private final ErrorResponseHandler errorResponseHandler;

    /**
     *
     * @param errorResponseHandler Error Response Handler.
     */
    public AuthorizationCodeController(ErrorResponseHandler errorResponseHandler) {
        this.errorResponseHandler = errorResponseHandler;
    }

    /**
     * Callback action accessible through an Http Post request.
     * @param formFields A Map encapsulating the form url encoded payload.
     * @return An HttpResponse.
     */
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Post("${" + AuthorizationCodeControllerConfigurationProperties.PREFIX + ".action-path:/cb}")
    public Single<HttpResponse> cbPost(@Body Map formFields) {
        if (isErrorResponse(formFields)) {
            ErrorResponse errorResponse = new ErrorResponseMapAdapter(formFields);
            return errorResponseHandler.handle(errorResponse);
        }

        return Single.just(HttpResponse.ok());
    }

    /**
     * Callback action accessible through an Http Get request.
     * @param parameters Http parameters
     * @return An HttpResponse.
     */
    @Get("${" + AuthorizationCodeControllerConfigurationProperties.PREFIX + ".action-path:/cb}")
    public Single<HttpResponse> cbGet(HttpParameters parameters) {
        if (isErrorResponse(parameters)) {
            ErrorResponse errorResponse = new ErrorResponseHttpParamsAdapter(parameters);
            return errorResponseHandler.handle(errorResponse);
        }

        return Single.just(HttpResponse.ok());
    }

    /**
     *
     * @param parameters Http parameters
     * @return true if the response is consider an error.
     */
    protected boolean isErrorResponse(HttpParameters parameters) {
        return parameters.get("error", String.class).isPresent();
    }

    /**
     *
     * @param formFields A Map encapsulating the form url encoded payload.
     * @return true if the response is consider an error.
     */
    protected boolean isErrorResponse(Map formFields) {
        Object value = formFields.get(ErrorResponse.JSON_KEY_ERROR);
        if (value instanceof String) {
            return true;
        }
        return false;
    }
}
