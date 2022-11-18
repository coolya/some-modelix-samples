package org.modelix.sample.restapimodelserver

import University.Schedule.structure.Courses
import University.Schedule.structure.Rooms
import org.modelix.model.lazy.INodeReferenceSerializer

/**
 * A Kotlin extension function to convert a model lecture to its REST representation enforced by the generated
 * data class [Lecture].
 */
fun University.Schedule.structure.Lecture.toRest() = Lecture(
    lectureRef = INodeReferenceSerializer.serialize(this.reference),
    name = this.properties.name ?: "",
    description = this.properties.description ?: "",
    maxParticipants = this.properties.maxParticipants ?: 0,
    room = INodeReferenceSerializer.serialize(this.references.room.reference),
)

/**
 * A Kotlin extension function to convert a model room to its REST representation enforced by the generated
 * data class [Room].
 */
fun University.Schedule.structure.Room.toRest() = Room(
    roomRef = INodeReferenceSerializer.serialize(this.reference),
    name = this.properties.name ?: "",
    maxPlaces = this.properties.maxPlaces ?: 0,
    hasRemoteEquipment = this.properties.hasRemoteEquipment ?: false
)

fun Rooms.toRest() = RoomList(this.children.rooms.map { it.toRest() })

fun Courses.toRest() = LectureList(this.children.lectures.map { it.toRest() })