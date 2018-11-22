package io.micronaut.security.oauth2.endpoints

import io.micronaut.context.ApplicationContext
import io.micronaut.context.env.Environment
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.RxHttpClient
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.runtime.server.EmbeddedServer
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

class AuthorizationCodeControllerPathSpec extends Specification {

    @Shared
    @AutoCleanup
    EmbeddedServer embeddedServer = ApplicationContext.run(EmbeddedServer, [
            'micronaut.security.enabled': true,
            'micronaut.security.endpoints.authcode.controller-path': '/cb',
            'micronaut.security.endpoints.authcode.action-path': '/',
    ], Environment.TEST)

    @Shared
    @AutoCleanup
    RxHttpClient client = embeddedServer.applicationContext.createBean(RxHttpClient, embeddedServer.getURL())

    void "AuthorizationCodeController is no longer accessible at /authcode/cb"() {
        when:
        client.toBlocking().exchange(HttpRequest.GET('/authcode/cb'))

        then:
        HttpClientResponseException e = thrown(HttpClientResponseException)
        e.status == HttpStatus.UNAUTHORIZED
    }

    void "AuthorizationCodeController is accessible at /cb"() {
        when:
        embeddedServer.applicationContext.getBean(AuthorizationCodeController)

        then:
        noExceptionThrown()

        when:
        client.toBlocking().exchange(HttpRequest.GET('/cb'))

        then:
        noExceptionThrown()
    }
}
