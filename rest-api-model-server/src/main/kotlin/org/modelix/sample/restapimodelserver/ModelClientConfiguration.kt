package org.modelix.sample.restapimodelserver

import io.smallrye.config.ConfigMapping
import io.smallrye.config.WithDefault
import java.net.URI
import java.util.*

/**
 * Describes configuration properties required to access a model-server instance using the client code.
 *
 * Cf. https://quarkus.io/guides/config-mappings for the underlying Quarkus configuration mechanism.
 */
@ConfigMapping(prefix = "modelix.client")
interface ModelClientConfiguration {
    @WithDefault(value = "http://0.0.0.0:28101")
    fun serverUri(): URI

    @WithDefault(value = "courses")
    fun repositoryId(): String

    @WithDefault(value = "master")
    fun branch(): String

    fun user(): Optional<String>
}

