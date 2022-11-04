package org.modelix.sample.restapijsonbulk

// Use this file to hold package-level internal functions that return receiver object passed to the `install` method.
import io.ktor.server.auth.*

// Defines authentication mechanisms used throughout the application.
val ApplicationAuthProviders: Map<String, OAuthServerSettings> = listOf<OAuthServerSettings>(
//        OAuthServerSettings.OAuth2ServerSettings(
//                name = "facebook",
//                authorizeUrl = "https://graph.facebook.com/oauth/authorize",
//                accessTokenUrl = "https://graph.facebook.com/oauth/access_token",
//                requestMethod = HttpMethod.Post,
//
//                clientId = settings.property("auth.oauth.facebook.clientId").getString(),
//                clientSecret = settings.property("auth.oauth.facebook.clientSecret").getString(),
//                defaultScopes = listOf("public_profile")
//        )
).associateBy { it.name }
