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

package io.micronaut.security.oauth2.openid.endpoints.authorization;

/**
 * Authentication Flows response types.
 *
 *
 * @author Sergio del Amo
 * @since 1.1.0
 */
public enum ResponseType {

    CODE("code"),
    ID_TOKEN("id_token"),
    TOKEN("token");

    private String responseType;

    /**
     * Instantiates Response Type.
     * @param responseType Response type
     */
    ResponseType(String responseType) {
        this.responseType = responseType;
    }

    /**
     *
     * @return Authentication type response type.
     */
    public String getResponseType() {
        return responseType;
    }
}