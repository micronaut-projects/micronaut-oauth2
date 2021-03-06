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

package io.micronaut.security.oauth2.openid.endpoints.authorization.state;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.net.URI;

/**
 * Represents the state sent in the authorization request and returned in the authorization response.
 *
 * @author James Kleeh
 * @since 1.0.0
 */
public interface State {

    /**
     * @return The URI that was redirected from
     */
    @Nullable
    URI getOriginalUri();

    /**
     * @return A nonce value
     */
    @Nonnull
    String getNonce();
}
