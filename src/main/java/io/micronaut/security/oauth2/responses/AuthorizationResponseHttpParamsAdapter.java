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

package io.micronaut.security.oauth2.responses;

import io.micronaut.http.HttpParameters;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;

/**
 * Adapts from {@link HttpParameters} to {@link AuthorizationResponse}.
 *
 * @author Sergio del Amo
 * @since 1.1.0
 */
public class AuthorizationResponseHttpParamsAdapter implements AuthorizationResponse {

    private final HttpParameters httpParameters;

    /**
     * Constructs an adapter from {@link HttpParameters} to {@link ErrorResponse}.
     * @param httpParameters Http Parameters
     */
    public AuthorizationResponseHttpParamsAdapter(HttpParameters httpParameters) {
        this.httpParameters = httpParameters;
    }

    @Nullable
    @Override
    public String getState() {
        return httpParameters.get(AuthorizationResponse.KEY_STATE);
    }

    @Nonnull
    @Override
    public String getCode() {
        return Objects.requireNonNull(httpParameters.get(AuthorizationResponse.KEY_CODE));
    }
}
