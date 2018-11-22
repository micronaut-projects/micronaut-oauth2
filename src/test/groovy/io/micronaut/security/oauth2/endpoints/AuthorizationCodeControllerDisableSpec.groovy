package io.micronaut.security.oauth2.endpoints

import io.micronaut.context.ApplicationContext
import io.micronaut.context.env.Environment
import io.micronaut.context.exceptions.NoSuchBeanException
import spock.lang.Specification

class AuthorizationCodeControllerDisableSpec extends Specification {
    void "AuthorizationCodeController can be disabled with micronaut.security.endpoints.authcode.enabled=false"() {
        given:
        ApplicationContext context = ApplicationContext.run([
                'micronaut.security.enabled': true,
                'micronaut.security.endpoints.authcode.enabled': false,
        ], Environment.TEST)

        when:
        context.getBean(AuthorizationCodeController)

        then:
        thrown(NoSuchBeanException)

        cleanup:
        context.close()
    }

    void "AuthorizationCodeController is loaded by default"() {
        given:
        ApplicationContext context = ApplicationContext.run([
                'micronaut.security.enabled': true,
        ], Environment.TEST)

        when:
        context.getBean(AuthorizationCodeController)

        then:
        noExceptionThrown()

        cleanup:
        context.close()
    }

    void "AuthorizationCodeController can be disabled with micronaut.security.oauth2.authorization.response-type=id-token"() {
        given:
        ApplicationContext context = ApplicationContext.run([
                'micronaut.security.enabled': true,
                'micronaut.security.oauth2.authorization.response-type': 'id-token',
        ], Environment.TEST)

        when:
        context.getBean(AuthorizationCodeController)

        then:
        thrown(NoSuchBeanException)

        cleanup:
        context.close()
    }

    void "AuthorizationCodeController can be disabled with micronaut.security.oauth2.token.grant-type=implicit"() {
        given:
        ApplicationContext context = ApplicationContext.run([
                'micronaut.security.enabled': true,
                'micronaut.security.oauth2.token.grant-type': 'implicit',
        ], Environment.TEST)

        when:
        context.getBean(AuthorizationCodeController)

        then:
        thrown(NoSuchBeanException)

        cleanup:
        context.close()
    }
}
