package org.modelix.sample.restapijsonbulk.models.apis

import University.Schedule.structure.Courses
import University.Schedule.structure.Rooms
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.locations.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import jetbrains.mps.lang.core.structure.BaseConcept
import org.modelix.model.api.INode
import org.modelix.model.api.INodeReference
import org.modelix.model.lazy.INodeReferenceSerializer
import org.modelix.mps.apigen.runtime.MPSLanguageRegistry
import org.modelix.sample.restapijsonbulk.Paths
import org.modelix.sample.restapijsonbulk.models.Lecture
import org.modelix.sample.restapijsonbulk.models.LectureList
import org.modelix.sample.restapijsonbulk.models.Room
import org.modelix.sample.restapijsonbulk.models.RoomList
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.net.URLEncoder
import java.nio.charset.Charset

val logger: Logger = LoggerFactory.getLogger("BulkApi")

object RouteHelper {
    @JvmStatic
    public fun urlEncode(input: String): String {
        return URLEncoder.encode(input, Charset.defaultCharset())!!
    }
}

fun Route.BulkApi(loadRoots: suspend () -> List<INode>, resolve: suspend (INodeReference) -> INode?) {
    get<Paths.getLectures> {
        val roots = loadRoots()
        val allLectures = roots.flatMap {
            (it.allChildren.map { MPSLanguageRegistry.getInstance<BaseConcept>(it) })
        }.filterIsInstance<Courses>().flatMap { it.children.lectures }

        val roomList = LectureList(lectures = allLectures.map {
            Lecture(name = it.properties.name!!,
                    description = it.properties.description!!,
                    lectureRef = RouteHelper.urlEncode(INodeReferenceSerializer.serialize(it.iNode.reference)),
                    room = RouteHelper.urlEncode(INodeReferenceSerializer.serialize(it.references.room.iNode.reference)),
                    maxParticipants = it.properties.maxParticipants!!)
        })
        call.respond(roomList)
    }

    get<Paths.getLecturesLectureRef> {
        try {
            val lectureRef = INodeReferenceSerializer.deserialize(call.parameters["lectureRef"]!!.decodeURLPart())
            val iNode = resolve(lectureRef)!!
            val instance = MPSLanguageRegistry.getInstance<University.Schedule.structure.Lecture>(iNode)!!
            val lecture = Lecture(name = instance.properties.name!!,
                    maxParticipants = instance.properties.maxParticipants!!,
                    lectureRef = RouteHelper.urlEncode(INodeReferenceSerializer.serialize(instance.iNode.reference)),
                    room = RouteHelper.urlEncode(INodeReferenceSerializer.serialize(instance.references.room.reference)),
                    description = instance.properties.description!!
            )
            call.respond(lecture)
        } catch (e: RuntimeException) {
            call.respond(HttpStatusCode.NotFound, "Can not load Room: " + e.message)
        }
    }

    get<Paths.getRooms> {
        val roots = loadRoots()
        val allRooms = roots.flatMap { (it.allChildren.map { MPSLanguageRegistry.getInstance<BaseConcept>(it) }) }
                .filterIsInstance<Rooms>()
                .flatMap { it.children.rooms }

        val roomList = RoomList(rooms = allRooms.map {
            Room(name = it.properties.name!!,
                    maxPlaces = it.properties.maxPlaces!!,
                    roomRef = RouteHelper.urlEncode(INodeReferenceSerializer.serialize(it.iNode.reference)),
                    hasRemoteEquipment = it.properties.hasRemoteEquipment)
        })
        call.respond(roomList)
    }


    get<Paths.getRoomsRoomID> {
        try {
            val roomRef = INodeReferenceSerializer.deserialize(call.parameters["roomRef"]!!.decodeURLPart())
            val iNode = resolve(roomRef)!!
            val instance = MPSLanguageRegistry.getInstance<University.Schedule.structure.Room>(iNode)!!
            val room = Room(name = instance.properties.name!!,
                    roomRef = RouteHelper.urlEncode(INodeReferenceSerializer.serialize(instance.iNode.reference)),
                    maxPlaces = instance.properties.maxPlaces!!,
                    hasRemoteEquipment = instance.properties.hasRemoteEquipment
            )
            call.respond(room)
        } catch (e: RuntimeException) {
            call.respond(HttpStatusCode.NotFound, "Can not load Room: " + e.message)
        }
    }
}
