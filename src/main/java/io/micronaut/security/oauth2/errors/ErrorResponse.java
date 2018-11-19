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

package io.micronaut.security.oauth2.errors;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Oauth 2.0 Error Response.
 *
 * @see <a href="https://tools.ietf.org/html/rfc6749#section-5.2">RFC 6749 - Error Response</a>
 *
 * @author Sergio del Amo
 * @version 1.1.0
 */
public class ErrorResponse {

    @Nonnull
    private Error error;

    @Nullable
    @JsonProperty("error_description")
    private String errorDescription;

    @Nullable
    @JsonProperty("error_uri")
    private String errorUri;

    /**
     * Constructor.
     */
    public ErrorResponse() {

    }

    /**
     *
     * @return The error code
     */
    @Nonnull
    public Error getError() {
        return error;
    }

    /**
     *
     * @param error The error code.
     */
    public void setError(Error error) {
        this.error = error;
    }

    /**
     *
     * @return Human-readable ASCII [USASCII] text providing additional information, used to assist the client developer in
     *  understanding the error that occurred.
     */
    @Nullable
    public String getErrorDescription() {
        return errorDescription;
    }


    /**
     *
      * @param errorDescription Human-readable ASCII [USASCII] text providing additional information about the error.
     */
    public void setErrorDescription(@Nullable String errorDescription) {
        this.errorDescription = errorDescription;
    }

    /**
     *
     * @return URI identifying a human-readable web page with information about the error
     */
    @Nullable
    public String getErrorUri() {
        return errorUri;
    }

    /**
     *
     * @param errorUri URI identifying a human-readable web page with information about the error.
     */
    public void setErrorUri(@Nullable String errorUri) {
        this.errorUri = errorUri;
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "error='" + error + '\'' +
                ", errorDescription='" + errorDescription + '\'' +
                ", errorUri='" + errorUri + '\'' +
                '}';
    }
}
