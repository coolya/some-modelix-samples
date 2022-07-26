package org.modelix.samples.some

import MPSRemoteClient
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.future.future
import org.modelix.model.api.INodeReference

class JavaInteropMPSRemoteClient(val wrapped: MPSRemoteClient) {
    fun getViewModels() = GlobalScope.future {
        wrapped.getViewModels()
    }

    fun loadModelAreas(modelIds: List<String>) = GlobalScope.future {
        wrapped.loadModelAreas(modelIds)
    }

    fun loadModelArea(modelId: String) = GlobalScope.future {
        wrapped.loadModelArea(modelId)
    }

    fun resolveReference(ref: INodeReference) = wrapped.resolveReference(ref)
}