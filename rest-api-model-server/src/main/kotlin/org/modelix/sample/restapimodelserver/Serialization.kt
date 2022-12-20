package org.modelix.sample.restapimodelserver

import University.Schedule.N_Courses
import University.Schedule.N_Lecture
import University.Schedule.N_Room
import University.Schedule.N_Rooms
import org.modelix.model.api.serialize


/**
 * A Kotlin extension function to convert a model lecture to its JSON representation enforced by the generated
 * data class [Lecture].
 */
fun N_Lecture.toJson() = Lecture(
        lectureRef = this.unwrap().reference.serialize(),
        name = this.name!!,
        description = this.description!!,
        maxParticipants = this.maxParticipants,
        room = this.room!!.unwrap().reference.serialize(),
)

/**
 * A Kotlin extension function to convert a model room to its JSON representation enforced by the generated
 * data class [Room].
 */
fun N_Room.toJson() = Room(
        roomRef = this.unwrap().reference.serialize(),
        name = this.name!!,
        maxPlaces = this.maxPlaces,
        hasRemoteEquipment = this.hasRemoteEquipment
)

fun List<N_Room>.toJson() = RoomList(this.map { it.toJson() })

fun List<N_Lecture>.toJson() = LectureList(this.map { it.toJson() })

enum class WhatChanged {
    ROOM,
    ROOM_LIST,
    LECTURE,
    LECTURE_LIST
}

data class ChangeNotification(
        val whatChanged: WhatChanged,
        val change: Any
)