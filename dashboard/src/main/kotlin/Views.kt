package org.modelix.sample.dashboard

import University.Schedule.structure.Courses
import University.Schedule.structure.Lecture
import University.Schedule.structure.Room
import kotlinx.html.*
import org.modelix.model.lazy.INodeReferenceSerializer
import java.net.URLEncoder


fun HTML.landingPage(allRooms: List<Room>, allLectures: List<Lecture>) {
    val encoder =
    head {
        title = "Dashboard"
        styleLink("/styles/styles.css")
    }
    body("container mx-auto") {
        div("min-h-screen mx-auto flex flex-col flex justify-center space-y-3 text-3xl") {

            details("text-center") {
                summary() {
                    +"Rooms"
                }
                ul {
                    allRooms.forEach {
                        li {
                            a {
                                val refString = INodeReferenceSerializer.serialize(it.iNode.reference)
                                href = "/rooms/${refString.let { URLEncoder.encode(it, Charsets.UTF_8) }}"
                                +it.safeName
                            }
                        }
                    }
                }
            }

            details("text-center") {
                summary() {
                    +"Lectures"
                }
                ul {
                    allLectures.forEach {
                        li {
                            a {
                                val refString = INodeReferenceSerializer.serialize(it.iNode.reference)
                                href = "/lectures/${refString.let { URLEncoder.encode(it, Charsets.UTF_8) }}"
                                +it.safeName
                            }
                        }
                    }
                }
            }
        }
    }
}



fun HTML.room(room: Room, lectures: List<Lecture>) {
    body {
        table {
            thead {
                tr {
                    th {
                      scope = ThScope.col
                      +room.safeName
                    }
                }
                tr {
                    th {
                        scope = ThScope.col
                        +"Time"
                    }
                    th {
                        scope = ThScope.col
                        +"Lecture"
                    }
                }
            }
            tbody {
                lectures.filter { it.references.room == room }.forEach { lecture ->
                    tr {
                        td {
                            lecture.children.schedule.readable()
                        }
                        td {
                            +lecture.safeName
                        }
                    }
                }
            }
        }
    }
}

fun HTML.lecture(lecture: Lecture) {
    body {
        h1 { +"its a lecture"}
    }
}