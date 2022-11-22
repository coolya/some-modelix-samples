package org.modelix.sample.restapimodelserver

import University.Schedule.structure.Courses
import University.Schedule.structure.Rooms
import org.modelix.model.lazy.INodeReferenceSerializer

/**
 * A Kotlin extension function to convert a model lecture to its JSON representation enforced by the generated
 * data class [Lecture].
 */
fun University.Schedule.structure.Lecture.toJson() = Lecture(
    lectureRef = INodeReferenceSerializer.serialize(this.reference),
    name = this.properties.name ?: "",
    description = this.properties.description ?: "",
    maxParticipants = this.properties.maxParticipants ?: 0,
    room = INodeReferenceSerializer.serialize(this.references.room.reference),
)

/**
 * A Kotlin extension function to convert a model room to its JSON representation enforced by the generated
 * data class [Room].
 */
fun University.Schedule.structure.Room.toJson() = Room(
    roomRef = INodeReferenceSerializer.serialize(this.reference),
    name = this.properties.name ?: "",
    maxPlaces = this.properties.maxPlaces ?: 0,
    hasRemoteEquipment = this.properties.hasRemoteEquipment ?: false
)

fun Rooms.toJson() = RoomList(this.children.rooms.map { it.toJson() })

fun Courses.toJson() = LectureList(this.children.lectures.map { it.toJson() })

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