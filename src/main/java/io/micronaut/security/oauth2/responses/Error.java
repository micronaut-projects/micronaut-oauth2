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

/**
 * @author Sergio del Amo
 * @since 1.1.0
 */
public enum Error {

    INVALID_REQUEST("invalid_request"),
    INVALID_CLIENT("invalid_client"),
    INVALID_GRANT("invalid_grant"),
    UNAUTHORIZED_CLIENT("unauthorized_client"),
    UNSUPPORTED_GRANT_TYPE("unsupported_grant_type"),
    INVALID_SCOPE("invalid_scope");

    private String error;

    /**
     *
     * @param error Error code
     */
    Error(String error) {
        this.error = error;
    }

    /**
     *
     * @return An error code.
     */
    public String getError() {
        return error;
    }

    /**
     *
     * @param error the error code
     */
    public void setError(String error) {
        this.error = error;
    }
}
