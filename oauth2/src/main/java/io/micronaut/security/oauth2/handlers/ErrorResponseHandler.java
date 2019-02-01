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

import io.micronaut.http.HttpResponse;
import io.micronaut.security.oauth2.responses.ErrorResponse;
import io.reactivex.Single;

/**
 * Handles Oauth 2.0 Error responses.
 *
 * @author Sergio del Amo
 * @since 1.0.0
 */
public interface ErrorResponseHandler {

    /**
     * Handles an OAuth 2.0 error.
     *
     * @param errorResponse OAuth 2.0 Error Response
     * @return A Http Response
     */
    Single<HttpResponse<?>> handle(ErrorResponse errorResponse);
}
