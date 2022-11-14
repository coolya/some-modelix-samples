package org.modelix.sample

import org.modelix.model.client.ReplicatedRepository
import org.modelix.model.client.RestWebModelClient
import org.modelix.model.lazy.RepositoryId
import javax.enterprise.context.ApplicationScoped

/**
 * Produces the CDI beans necessary for interacting with a model-server instance.
 */
class ModelClientProducer {

    @ApplicationScoped
    fun modelClient(config: ModelClientConfiguration): RestWebModelClient =
        RestWebModelClient(config.serverUri().toString())

    @ApplicationScoped
    fun repo(client: RestWebModelClient, config: ModelClientConfiguration): ReplicatedRepository =
        ReplicatedRepository(client, RepositoryId(config.repositoryId()), config.branch()) {
            config.user().orElse(null)
        }
}