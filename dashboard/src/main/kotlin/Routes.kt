package org.modelix.sample.dashboard

import University.Schedule.structure.Courses
import University.Schedule.structure.Lecture
import University.Schedule.structure.Room
import University.Schedule.structure.Rooms
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.http.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import jetbrains.mps.lang.core.structure.BaseConcept
import org.modelix.model.api.INode
import org.modelix.model.api.INodeReference
import org.modelix.model.lazy.INodeReferenceSerializer
import org.modelix.mps.apigen.runtime.MPSLanguageRegistry

fun Application.configureRouting(loadRoots: suspend () -> List<INode>, resolve: suspend (INodeReference) -> INode?) = routing {

    static("/styles") {
        resources("styles")
    }
    get("/") {
        val roots = loadRoots()
        val allRooms = roots.flatMap { (it.allChildren.map { MPSLanguageRegistry.getInstance<BaseConcept>(it) }) }
            .filterIsInstance<Rooms>()
            .flatMap { it.children.rooms }

        val allLectures = roots.flatMap { (it.allChildren.map { MPSLanguageRegistry.getInstance<BaseConcept>(it) }) }
            .filterIsInstance<Courses>()
            .flatMap { it.children.lectures }

        call.respondHtml {
            landingPage(allRooms, allLectures)
        }
    }
    get("/rooms/{roomRef}") {
        val roomRefString = call.parameters["roomRef"]
        if (roomRefString == null) {
            call.respond(HttpStatusCode.BadRequest, "missing room ref")
            return@get
        }

        val roots = loadRoots()
        val roomRef = INodeReferenceSerializer.deserialize(roomRefString.decodeURLPart())
        val iNode = resolve(roomRef)

        if(iNode == null) {
            call.respond(HttpStatusCode.NotFound, "can't find room")
            return@get
        }

        val instance = MPSLanguageRegistry.getInstance<Room>(iNode)

        if(instance == null) {
            call.respond(HttpStatusCode.NotFound, "Can't load room")
            return@get
        }

        val allLectures = roots.flatMap { (it.allChildren.map { MPSLanguageRegistry.getInstance<BaseConcept>(it) }) }
            .filterIsInstance<Courses>()
            .flatMap { it.children.lectures }

        call.respondHtml {
            room(instance, allLectures)
        }
    }

    get("/lectures/{lectureRef}") {
        val lectureRefStr = call.parameters["lectureRef"]
        if (lectureRefStr == null) {
            call.respond(HttpStatusCode.BadRequest, "missing lecture ref")
            return@get
        }

        val lectureRef = INodeReferenceSerializer.deserialize(lectureRefStr.decodeURLPart())
        val iNode = resolve(lectureRef)

        if(iNode == null) {
            call.respond(HttpStatusCode.NotFound, "can't find lecture")
            return@get
        }

        val instance = MPSLanguageRegistry.getInstance<Lecture>(iNode)

        if(instance == null) {
            call.respond(HttpStatusCode.NotFound, "Can't load lecture")
            return@get
        }

        call.respondHtml {
            lecture(instance)
        }
    }
}