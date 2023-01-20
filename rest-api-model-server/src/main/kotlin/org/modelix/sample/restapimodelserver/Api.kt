package org.modelix.sample.restapimodelserver

import University.Schedule.*
import jetbrains.mps.lang.core.N_BaseConcept
import org.modelix.metamodel.ITypedConcept
import org.modelix.metamodel.typed
import org.modelix.model.api.INode
import org.modelix.model.api.INodeReference
import org.modelix.model.api.INodeReferenceSerializer
import org.modelix.model.area.PArea
import org.modelix.model.client.ReplicatedRepository
import org.modelix.model.repositoryconcepts.N_Module
import javax.ws.rs.BadRequestException
import javax.ws.rs.NotFoundException

/**
 * Implementation of the REST API.
 *
 * The structure of the REST API and the used data classes are generated from the OpenAPI specification using a
 * Gradle plugin. [DefaultApi] is one result of the generation process
 */
class Api(private val repo: ReplicatedRepository) : DefaultApi {

    fun <T> executeRead(func: (area: PArea) -> T): T {
        val area = PArea(repo.branch)
        return area.executeRead {
            func(area)
        }
    }

    private fun getAllRootNodes(area: PArea): List<N_BaseConcept> {
        return area.getRoot().allChildren.map { it.typed() }.filterIsInstance<N_Module>().flatMap { it.models }.flatMap { it.rootNodes }
    }

    /**
     * Deserializes a client-provided node reference. If this fails, a [BadRequestException] is thrown.
     * The client most likely provided an invalid reference format.
     */
    private fun safeDeserializeRefString(lectureRef: String): INodeReference {
        return try {
            INodeReferenceSerializer.deserialize(lectureRef)
        } catch (e: RuntimeException) {
            throw BadRequestException("Invalid reference format given: $lectureRef", e)
        }
    }

    private fun resolveRef(refString: String, area: PArea): INode {
        val ref = safeDeserializeRefString(refString)
        return ref.resolveNode(area) ?: throw NotFoundException("No entity known with ref $refString")
    }

    override fun getLectureByRef(lectureRef: String): Lecture = executeRead { area ->
        val node = resolveRef(lectureRef, area)
        val lecture = node.typed<N_Lecture>()
        lecture.toJson()
   }

    override fun getRoomByRef(roomRef: String): Room = executeRead { area ->
        val node = resolveRef(roomRef, area)
        val room = node.typed<N_Room>()
        room.toJson()
    }

    override fun listLectures(): LectureList = executeRead { area ->
        getAllRootNodes(area).filterIsInstance<N_Courses>().flatMap { it.lectures }.toJson()
    }

    override fun listRooms(): RoomList = executeRead { area ->
        getAllRootNodes(area).filterIsInstance<N_Rooms>().flatMap { it.rooms }.toJson()
    }

}
