package org.modelix.sample.restapijsonbulk.models.apis

import University.Schedule.N_Lecture
import University.Schedule.N_Room
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.locations.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.modelix.client.light.LightModelClient
import org.modelix.sample.restapimodelql.ModelServerLightWrapper
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

fun Route.ModelQLAPI(mslw: ModelServerLightWrapper) {
    get<Paths.getLectures> {
        val allLectures: List<N_Lecture> = mslw.getAllLectures()
        var lectureList = LectureList()
        mslw.globalModelClient.runRead {
            lectureList = LectureList(lectures = allLectures.map { lectureInstance ->
                Lecture(name = lectureInstance.name,
                        description = lectureInstance.description,
                        lectureRef = RouteHelper.urlEncode((lectureInstance.unwrap().reference as LightModelClient.NodeAdapter).nodeId),
                        room = RouteHelper.urlEncode((lectureInstance.room.unwrap().reference as LightModelClient.NodeAdapter).nodeId),
                        maxParticipants = lectureInstance.maxParticipants)
            })
        }
        call.respond(lectureList)
    }

    get<Paths.getLecturesLectureRef> {
        try {
            val zheLecture: N_Lecture = mslw.resolveNodeIdToConcept(call.parameters["lectureRef"]!!.decodeURLPart())!! as N_Lecture
            var lecture: Lecture? = Lecture("", "", "", 0, "")

            mslw.globalModelClient.runRead {
                lecture = Lecture(name = zheLecture.name,
                        maxParticipants = zheLecture.maxParticipants,
                        lectureRef = RouteHelper.urlEncode((zheLecture.unwrap().reference as LightModelClient.NodeAdapter).nodeId),
                        room = RouteHelper.urlEncode((zheLecture.room.unwrap().reference as LightModelClient.NodeAdapter).nodeId),
                        description = zheLecture.description
                )
            }
            call.respond(lecture!!)
        } catch (e: RuntimeException) {
            call.respond(HttpStatusCode.NotFound, "Can not load Lecture: " + e.message)
        }
    }

    get<Paths.getRooms> {
        val allRooms: List<N_Room> = mslw.getAllRooms()
        var roomList = RoomList()
        mslw.globalModelClient.runRead {
            roomList = RoomList(rooms = allRooms.map { roomInstance ->
                Room(name = roomInstance.name,
                        maxPlaces = roomInstance.maxPlaces,
                        roomRef = RouteHelper.urlEncode((roomInstance.unwrap().reference as LightModelClient.NodeAdapter).nodeId),
                        hasRemoteEquipment = roomInstance.hasRemoteEquipment)
            })
        }
        call.respond(roomList)
    }

    get<Paths.getRoomsRoomID> {
        try {
            val zheRoom: N_Room = mslw.resolveNodeIdToConcept(call.parameters["roomRef"]!!.decodeURLPart())!! as N_Room
            var room: Room? = Room("", "", 0, null)

            mslw.globalModelClient.runRead {
                room = Room(name = zheRoom.name,
                        roomRef = RouteHelper.urlEncode((zheRoom.unwrap().reference as LightModelClient.NodeAdapter).nodeId),
                        maxPlaces = zheRoom.maxPlaces,
                        hasRemoteEquipment = zheRoom.hasRemoteEquipment
                )
            }
            call.respond(room!!)
        } catch (e: RuntimeException) {
            call.respond(HttpStatusCode.NotFound, "Can not load Room: " + e.message)
        }
    }
}
