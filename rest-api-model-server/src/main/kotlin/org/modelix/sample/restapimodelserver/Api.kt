package org.modelix.sample.restapimodelserver

import University.Schedule.*
import jetbrains.mps.lang.core.C_BaseConcept
import jetbrains.mps.lang.core.N_BaseConcept
import org.modelix.metamodel.ITypedConcept
import org.modelix.metamodel.typed
import org.modelix.model.api.INode
import org.modelix.model.api.INodeReference
import org.modelix.model.api.INodeReferenceSerializer
import org.modelix.model.area.PArea
import org.modelix.model.client.ReplicatedRepository
import org.modelix.model.repositoryconcepts.N_Repository
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

    private fun allModelRoots(area: PArea): List<N_BaseConcept> {
        return (area.getRoot().typed() as N_Repository).modules.flatMap { it.models }.flatMap { it.rootNodes }
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

    /**
     * Ensures that the given [node] is of concept [ConceptType]. Otherwise, a [NotFoundException] is thrown.
     *
     * This method is used as a safety net in GET implementations for a specific reference of a certain type.
     * If the provided reference resolved to a node of a different concept, this is similar to not finding any
     * node from the REST client's point of view.
     */
    private inline fun <reified ConceptType : ITypedConcept> ensureIsDesiredConcept(
        node: INode,
        refString: String
    ) {
        if (node.concept !is ConceptType) {
            throw NotFoundException("No entity known with ref $refString")
        }
    }

    override fun getLectureByRef(lectureRef: String): Lecture = executeRead { area ->
        val node = resolveRef(lectureRef, area)
        ensureIsDesiredConcept<C_Lecture>(node, lectureRef)

        L_University_Schedule.Lecture.wrap(node).toJson()
    }

    override fun getRoomByRef(roomRef: String): Room = executeRead { area ->
        val node = resolveRef(roomRef, area)
        ensureIsDesiredConcept<C_Room>(node, roomRef)

        University.Schedule.L_University_Schedule.Room.wrap(node).toJson()
    }

    override fun listLectures(): LectureList = executeRead { area ->
        University.Schedule.L_University_Schedule.Courses.wrap(allModelRoots(area).first { it is C_Courses }.unwrap()).toJson()
    }

    override fun listRooms(): RoomList = executeRead { area ->
        University.Schedule.L_University_Schedule.Rooms.wrap(allModelRoots(area).first { it is N_Rooms }.unwrap()).toJson()
    }

}
