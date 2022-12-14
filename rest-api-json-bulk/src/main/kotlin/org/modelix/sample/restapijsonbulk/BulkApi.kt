package org.modelix.sample.restapijsonbulk.models.apis

//import University.Schedule.structure.Courses
//import University.Schedule.structure.Rooms
//import jetbrains.mps.lang.core.structure.BaseConcept
import University.Schedule.N_Courses
import University.Schedule.N_Lecture
import University.Schedule.N_Room
import University.Schedule.N_Rooms
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.locations.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.modelix.metamodel.typed
import org.modelix.model.api.INode
import org.modelix.model.api.INodeReference
import org.modelix.model.api.INodeReferenceSerializer
import org.modelix.model.api.serialize
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
            (it.allChildren.map { it.typed() })
        }.filterIsInstance<N_Courses>().flatMap { it.lectures }

        val roomList = LectureList(lectures = allLectures.map { lectureInstance ->
            Lecture(name = lectureInstance.name!!,
                    description = lectureInstance.description!!,
                    lectureRef = RouteHelper.urlEncode(lectureInstance.unwrap().reference.serialize()),
                    room = RouteHelper.urlEncode(lectureInstance.room!!.unwrap().reference.serialize()),
                    maxParticipants = lectureInstance.maxParticipants!!.toInt())
        })
        call.respond(roomList)
    }

    get<Paths.getLecturesLectureRef> {
        try {
            val lectureRef = INodeReferenceSerializer.deserialize(call.parameters["lectureRef"]!!.decodeURLPart())
            val iNode = resolve(lectureRef)!!
            val instance = iNode.typed() as N_Lecture

            val lecture = Lecture(name = instance.name!!,
                    maxParticipants = instance.maxParticipants!!.toInt(),
                    lectureRef = RouteHelper.urlEncode(instance.unwrap().reference.serialize()),
                    room = RouteHelper.urlEncode(instance.room!!.unwrap().reference.serialize()),
                    description = instance.description!!
            )
            call.respond(lecture)
        } catch (e: RuntimeException) {
            call.respond(HttpStatusCode.NotFound, "Can not load Room: " + e.message)
        }
    }

    get<Paths.getRooms> {
        val roots = loadRoots()
        val allRooms = roots.flatMap {
            (it.allChildren.map { it.typed() })
        }.filterIsInstance<N_Rooms>().flatMap { it.rooms }

        val roomList = RoomList(rooms = allRooms.map { roomInstance ->
            Room(name = roomInstance.name!!,
                    maxPlaces = roomInstance.maxPlaces!!.toInt(),
                    roomRef = RouteHelper.urlEncode(roomInstance.unwrap().reference.serialize()),
                    hasRemoteEquipment = roomInstance.hasRemoteEquipment.toBoolean())
        })
        call.respond(roomList)
    }


    get<Paths.getRoomsRoomID> {
        try {
            val roomRef = INodeReferenceSerializer.deserialize(call.parameters["roomRef"]!!.decodeURLPart())
            val iNode = resolve(roomRef)!!
            val instance = iNode.typed() as N_Room
            val room = Room(name = instance.name!!,
                    roomRef = RouteHelper.urlEncode(instance.unwrap().reference.serialize()),
                    maxPlaces = instance.maxPlaces!!.toInt(),
                    hasRemoteEquipment = instance.hasRemoteEquipment.toBoolean()
            )
            call.respond(room)
        } catch (e: RuntimeException) {
            call.respond(HttpStatusCode.NotFound, "Can not load Room: " + e.message)
        }
    }
}
