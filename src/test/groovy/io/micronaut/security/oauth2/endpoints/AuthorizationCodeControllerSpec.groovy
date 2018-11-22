package io.micronaut.security.oauth2.endpoints

import com.stehno.ersatz.ContentType
import com.stehno.ersatz.Encoders
import com.stehno.ersatz.ErsatzServer
import groovy.json.JsonOutput
import io.micronaut.context.ApplicationContext
import io.micronaut.context.env.Environment
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.MediaType
import io.micronaut.http.client.RxHttpClient
import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.security.oauth2.configuration.OauthConfiguration
import io.micronaut.security.oauth2.handlers.AuthorizationResponseHandler
import io.micronaut.security.oauth2.handlers.ErrorResponseHandler
import io.micronaut.security.oauth2.handlers.IdTokenAccessTokenResponseHandler
import io.micronaut.security.oauth2.handlers.NotValidIdTokenAccessTokenResponseHandler
import io.micronaut.security.oauth2.handlers.SuccessfulIdTokenAccessTokenResponseHandler
import io.micronaut.security.oauth2.openid.configuration.OpenIdProviderMetadata
import io.micronaut.security.oauth2.openid.endpoints.token.AuthorizationCodeGrantRequestGenerator
import io.micronaut.security.oauth2.openid.endpoints.token.DefaultAuthorizationCodeGrantRequestGenerator
import io.micronaut.security.oauth2.openid.endpoints.token.TokenEndpointConfiguration
import io.micronaut.security.oauth2.openid.idtoken.IdTokenAccessTokenResponseValidator
import io.micronaut.security.oauth2.responses.Oauth2AuthenticationResponse
import spock.lang.Specification

class AuthorizationCodeControllerSpec extends Specification {

    static final SPEC_NAME_PROPERTY = 'spec.name'

    void "Authorization Code Grant Flow"() {
        given:
        ErsatzServer ersatz = new ErsatzServer()
        String path = '/oauth2/default/v1/token'

        String tokenJson = JsonOutput.toJson([
                access_token: "SlAV32hkKG",
                token_type: "Bearer",
                refresh_token: "8xLOxBtZp8",
                expires_in: 3600,
                id_token: "eyJhbGciOiJSUzI1NiIsImtpZCI6IjFlOWdkazcifQ.ewogImlzcyI6ICJodHRwOi8vc2VydmVyLmV4YW1wbGUuY29tIiwKICJzdWIiOiAiMjQ4Mjg5NzYxMDAxIiwKICJhdWQiOiAiczZCaGRSa3F0MyIsCiAibm9uY2UiOiAibi0wUzZfV3pBMk1qIiwKICJleHAiOiAxMzExMjgxOTcwLAogImlhdCI6IDEzMTEyODA5Nz AKfQ.ggW8hZ1EuVLuxNuuIJKX_V8a_OMXzR0EHR9R6jgdqrOOF4daGU96Sr_P6qJp6IcmD3HP99Obi1PRs-cwh3LO-p146waJ8IhehcwL7F09JdijmBqkvPeB2T9CJNqeGpe-gccMg4vfKjkM8FcGvnzZUN4_KSP0aAp1tOJ1zZwgjxqGByKHiOtX7TpdQyHE5lcMiKPXfEIQILVq0pc_E2DzL7emopWoaoZTF_m0_N0YzFC6g6EJbOEoRoSK5hoDalrcvRYLSrQAZZKflyuVCyixEoV9GfNQC3_osjzw2PAithfubEEBLuVVk4XUVrWOLrLl0nx7RkKU8NXNHq-rvKMzqg"
        ])
        ersatz.expectations {
            post(path) {
                called 1
                responder {
                    encoder(ContentType.APPLICATION_JSON, Map, Encoders.json)
                    code(200)
                    body(tokenJson, "application/json")
                }
            }
        }
        String authorizationRedirectUri = "${ersatz.httpUrl}/authcode/cb"
        String tokenEndpoint = "${ersatz.httpUrl}${path}"
        Map<String, Object> config = [
                (SPEC_NAME_PROPERTY): getClass().simpleName,
                'micronaut.security.enabled': true,
                'micronaut.security.oauth2.client-id': 'XXX',
                'micronaut.security.oauth2.authorization.redirect-uri': authorizationRedirectUri,
                'micronaut.security.oauth2.token.redirect-uri': authorizationRedirectUri,
                'micronaut.security.oauth2.token.url': tokenEndpoint
        ]

        EmbeddedServer embeddedServer = ApplicationContext.run(EmbeddedServer, config, Environment.TEST)

        when:
        OauthConfiguration oauthConfiguration = embeddedServer.applicationContext.getBean(OauthConfiguration)

        then:
        noExceptionThrown()

        when:
        embeddedServer.applicationContext.getBean(OpenIdProviderMetadata)

        then:
        noExceptionThrown()

        when:
        TokenEndpointConfiguration tokenEndpointConfiguration = embeddedServer.applicationContext.getBean(TokenEndpointConfiguration)

        then:
        noExceptionThrown()
        tokenEndpointConfiguration.getRedirectUri() != null

        when:
        embeddedServer.applicationContext.getBean(AuthorizationCodeGrantRequestGenerator)

        then:
        noExceptionThrown()

        when:
        embeddedServer.applicationContext.getBean(DefaultAuthorizationCodeGrantRequestGenerator)

        then:
        noExceptionThrown()

        when:
        embeddedServer.applicationContext.getBean(ErrorResponseHandler)

        then:
        noExceptionThrown()

        when:
        embeddedServer.applicationContext.getBean(NotValidIdTokenAccessTokenResponseHandler)

        then:
        noExceptionThrown()

        when:
        embeddedServer.applicationContext.getBean(SuccessfulIdTokenAccessTokenResponseHandler)

        then:
        noExceptionThrown()

        when:
        embeddedServer.applicationContext.getBean(IdTokenAccessTokenResponseValidator)

        then:
        noExceptionThrown()

        when:
        embeddedServer.applicationContext.getBean(IdTokenAccessTokenResponseHandler)

        then:
        noExceptionThrown()

        when:
        embeddedServer.applicationContext.getBean(AuthorizationResponseHandler)

        then:
        noExceptionThrown()

        when:
        embeddedServer.applicationContext.getBean(AuthorizationCodeController)

        then:
        noExceptionThrown()

        when:
        RxHttpClient client = embeddedServer.applicationContext.createBean(RxHttpClient, embeddedServer.getURL())
        Oauth2AuthenticationResponse authenticationResponse = new Oauth2AuthenticationResponse()
        authenticationResponse.setCode("SplxlOBeZQQYbYS6WxSbIA")
        authenticationResponse.setState("af0ifjsldkj")
        HttpRequest request = HttpRequest.POST('/authcode/cb', authenticationResponse)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        HttpResponse rsp = client.toBlocking().exchange(request)

        then:
        rsp.status() == HttpStatus.OK

        then:
        ersatz.verify()

        cleanup:
        ersatz.stop()

        and:
        client.close()

        and:
        embeddedServer.close()
    }
}
