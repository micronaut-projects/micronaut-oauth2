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

package io.micronaut.security.oauth2.openid.endpoints.token;

import io.micronaut.context.BeanContext;
import io.micronaut.context.condition.Condition;
import io.micronaut.context.condition.ConditionContext;
import io.micronaut.context.exceptions.NoSuchBeanException;
import io.micronaut.security.oauth2.grants.GrantType;
import io.micronaut.security.oauth2.openid.configuration.OpenIdProviderMetadata;

/**
 * Conditions which verifies {@link TokenEndpointConfiguration#getGrantType()} is a particular grant type.
 *
 * @author Sergio del Amo
 * @since 1.1.0
 */
abstract public class TokenEndpointGrantTypeCondition implements Condition {

     @Override
     public boolean matches(ConditionContext context) {
            BeanContext beanContext = context.getBeanContext();
            try {
                TokenEndpointConfiguration tokenEndpointConfiguration = beanContext.getBean(TokenEndpointConfiguration.class);
                if (tokenEndpointConfiguration.getGrantType().equals(checkedGrantType().getGrantType())) {
                    return true;
                }
            } catch (NoSuchBeanException e) {
                return false;
            }
            return false;
    }

    /**
     *
     * @return The Oauth 2.0 Grant type.
     */
    abstract GrantType checkedGrantType();
}
