package org.modelix.sample.restapimodelserver

import University.Schedule.*
import org.modelix.model.api.INodeReferenceSerializer
import org.modelix.model.api.serialize


/**
 * A Kotlin extension function to convert a model lecture to its JSON representation enforced by the generated
 * data class [Lecture].
 */
fun N_Lecture.toJson() = Lecture(
        lectureRef = this.unwrap().reference.serialize(),
        name = this.name.toString(),
        description = this.description.toString(),
        maxParticipants = this.maxParticipants!!.toInt(),
        room = this.room!!.unwrap().reference.serialize(),
)

/**
 * A Kotlin extension function to convert a model room to its JSON representation enforced by the generated
 * data class [Room].
 */
fun N_Room.toJson() = Room(
    roomRef = this.unwrap().reference.serialize(),
        name = this.name.toString(),
    maxPlaces = this.maxPlaces!!.toInt(),
    hasRemoteEquipment = this.hasRemoteEquipment.toBoolean()
)

fun N_Rooms.toJson() = RoomList(this.rooms.map { it.toJson() })

fun N_Courses.toJson() = LectureList(this.lectures.map { it.toJson() })

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