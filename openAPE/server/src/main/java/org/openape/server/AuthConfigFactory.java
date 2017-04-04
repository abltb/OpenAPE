package org.openape.server;

import org.pac4j.core.config.Config;
import org.pac4j.core.config.ConfigFactory;
import org.pac4j.http.client.direct.ParameterClient;
import org.pac4j.jwt.config.signature.SecretSignatureConfiguration;
import org.pac4j.jwt.credentials.authenticator.JwtAuthenticator;

/**
 * Created by benjamin on 04.04.17.
 */
public class AuthConfigFactory implements ConfigFactory {

    private final String salt;

    public AuthConfigFactory(final String salt) {
        this.salt = salt;
    }

    @Override
    public Config build() {
        // REST authentication with JWT for a token passed in the url as the token parameter
        ParameterClient parameterClient = new ParameterClient("token", new JwtAuthenticator(new SecretSignatureConfiguration(salt)));
        parameterClient.setSupportGetRequest(true);
        parameterClient.setSupportPostRequest(false);
        // Define config
        final Config config = new Config(parameterClient);
        // Register HttpActionAdapter to respond with proper HTTP response codes on auth errors
        config.setHttpActionAdapter(new HttpActionAdapter());
        return config;
    }

}