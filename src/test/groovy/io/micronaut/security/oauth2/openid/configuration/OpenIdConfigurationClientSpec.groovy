package io.micronaut.security.oauth2.openid.configuration

import io.micronaut.context.ApplicationContext
import io.micronaut.context.env.Environment
import io.micronaut.context.exceptions.NoSuchBeanException
import spock.lang.Specification

class OpenIdConfigurationClientSpec extends Specification {
    static final SPEC_NAME_PROPERTY = 'spec.name'

    void "OpenIdConfigurationClient is disabled by default"() {
        given:
        ApplicationContext context = ApplicationContext.run([
                (SPEC_NAME_PROPERTY):getClass().simpleName,
                'micronaut.security.enabled': true,
        ], Environment.TEST)

        when:
        context.getBean(OpenIdConfigurationClient)

        then:
        thrown(NoSuchBeanException)

        cleanup:
        context.close()
    }

    void "OpenIdConfigurationClient bean is loaded if micronaut.security.oauth2.openid-configuration is set"() {
        given:
        ApplicationContext context = ApplicationContext.run([
                (SPEC_NAME_PROPERTY)                            : getClass().simpleName,
                'micronaut.security.enabled': true,
                'micronaut.security.oauth2.openid-configuration': 'https://micronaut.io'
        ], Environment.TEST)

        when:
        context.getBean(OpenIdConfigurationClient)

        then:
        noExceptionThrown()

        cleanup:
        context.close()
    }
}
