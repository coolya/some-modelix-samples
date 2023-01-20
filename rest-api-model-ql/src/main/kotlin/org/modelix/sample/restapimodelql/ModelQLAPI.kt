package org.modelix.sample.restapijsonbulk.models.apis

import ModelServerLightWrapper
import University.Schedule.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.locations.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.modelix.client.light.LightModelClient
import org.modelix.metamodel.typed
import org.modelix.model.api.INode
import org.modelix.model.api.INodeReference
import org.modelix.model.api.INodeReferenceSerializer
import org.modelix.model.api.serialize
import org.modelix.sample.restapimodelql.Paths
import org.modelix.sample.restapimodelql.models.Lecture
import org.modelix.sample.restapimodelql.models.LectureList
import org.modelix.sample.restapimodelql.models.Room
import org.modelix.sample.restapimodelql.models.RoomList
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

fun Route.ModelQLAPI(mslw : ModelServerLightWrapper) {
    get<Paths.getLectures> {

        val allLectures : List<N_Lecture> = mslw.getAllLectures()

        var roomList = LectureList()

        mslw.globalModelClient!!.runRead {
            roomList = LectureList(lectures = allLectures.map { lectureInstance ->
                Lecture(name = lectureInstance.name,
                        description = lectureInstance.description,
                        lectureRef = RouteHelper.urlEncode((lectureInstance.unwrap().reference as LightModelClient.NodeAdapter).nodeId),
                        room = RouteHelper.urlEncode((lectureInstance.room.unwrap().reference as LightModelClient.NodeAdapter).nodeId),
                        maxParticipants = lectureInstance.maxParticipants)
            })
        }
        call.respond(roomList)
    }

//    get<Paths.getLecturesLectureRef> {
//        try {
//            val lectureRef = INodeReferenceSerializer.deserialize(call.parameters["lectureRef"]!!.decodeURLPart())
//            val iNode = resolve(lectureRef)
//            val instance = iNode!!.typed<N_Lecture>()
//
//            val lecture = Lecture(name = instance.name,
//                    maxParticipants = instance.maxParticipants,
//                    lectureRef = RouteHelper.urlEncode(instance.unwrap().reference.serialize()),
//                    room = RouteHelper.urlEncode(instance.room.unwrap().reference.serialize()),
//                    description = instance.description
//            )
//            call.respond(lecture)
//        } catch (e: RuntimeException) {
//            call.respond(HttpStatusCode.NotFound, "Can not load Room: " + e.message)
//        }
//    }
//
//    get<Paths.getRooms> {
//        val roots = loadRoots()
//        val allRooms = roots.flatMap {
//            (it.allChildren.map { it.typed() })
//        }.filterIsInstance<N_Rooms>().flatMap { it.rooms }
//
//        val roomList = RoomList(rooms = allRooms.map { roomInstance ->
//            Room(name = roomInstance.name,
//                    maxPlaces = roomInstance.maxPlaces,
//                    roomRef = RouteHelper.urlEncode(roomInstance.unwrap().reference.serialize()),
//                    hasRemoteEquipment = roomInstance.hasRemoteEquipment)
//        })
//        call.respond(roomList)
//    }
//
//
//    get<Paths.getRoomsRoomID> {
//        try {
//            val roomRef = INodeReferenceSerializer.deserialize(call.parameters["roomRef"]!!.decodeURLPart())
//            val iNode = resolve(roomRef)
//            val instance = iNode!!.typed<N_Room>()
//
//                val room = Room(name = instance.name,
//                        roomRef = RouteHelper.urlEncode(instance.unwrap().reference.serialize()),
//                        maxPlaces = instance.maxPlaces,
//                        hasRemoteEquipment = instance.hasRemoteEquipment
//                )
//                call.respond(room)
//        } catch (e: RuntimeException) {
//            call.respond(HttpStatusCode.NotFound, "Can not load Room: " + e.message)
//        }
//    }
}
