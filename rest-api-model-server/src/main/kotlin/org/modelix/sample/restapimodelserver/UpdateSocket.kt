package org.modelix.sample.restapimodelserver

import University.Schedule.structure.Courses
import University.Schedule.structure.Lecture
import University.Schedule.structure.Room
import University.Schedule.structure.Rooms
import University.Schedule.structure.concepts.CoursesConcept
import University.Schedule.structure.concepts.LectureConcept
import University.Schedule.structure.concepts.RoomConcept
import University.Schedule.structure.concepts.RoomsConcept
import com.fasterxml.jackson.databind.ObjectMapper
import io.ktor.util.collections.*
import org.modelix.model.api.IBranchListener
import org.modelix.model.api.ITree
import org.modelix.model.api.ITreeChangeVisitor
import org.modelix.model.api.PNodeAdapter
import org.modelix.model.area.PArea
import org.modelix.model.client.ReplicatedRepository
import org.slf4j.LoggerFactory
import javax.enterprise.context.ApplicationScoped
import javax.websocket.OnClose
import javax.websocket.OnError
import javax.websocket.OnOpen
import javax.websocket.Session
import javax.websocket.server.ServerEndpoint

@ServerEndpoint("/updates")
@ApplicationScoped
class UpdateSocket(private val repo: ReplicatedRepository, private val mapper: ObjectMapper) {

    private val logger = LoggerFactory.getLogger(UpdateSocket::class.java)

    init {
        repo.branch.addListener(object : IBranchListener {
            override fun treeChanged(oldTree: ITree?, newTree: ITree) {
                logger.info("Processing repo change")

                if (oldTree == null) {
                    logger.info("Ignoring changes because no old tree exited")
                    return
                }

                newTree.visitChanges(oldTree, object : ITreeChangeVisitor {
                    override fun childrenChanged(nodeId: Long, role: String?) {
                        logger.debug("Children of node changed. [nodeId={}, role={}]", nodeId, role)
                        handleChange(nodeId)
                    }

                    override fun containmentChanged(nodeId: Long) {
                        logger.debug("Containment of node changed. [nodeId={}]", nodeId)
                        handleChange(nodeId)
                    }

                    override fun propertyChanged(nodeId: Long, role: String) {
                        logger.debug("Property of node changed. [nodeId={}, role={}]", nodeId, role)
                        handleChange(nodeId)
                    }

                    override fun referenceChanged(nodeId: Long, role: String) {
                        logger.debug("Reference of node changed. [nodeId={}, role={}]", nodeId, role)
                        handleChange(nodeId)
                    }

                })
            }
        })
    }

    private fun handleChange(nodeId: Long) {
        val area = PArea(repo.branch)
        val node = PNodeAdapter(nodeId, repo.branch)
        area.executeRead {
            when (node.concept) {
                is RoomConcept -> broadcast(ChangeNotification(WhatChanged.ROOM, Room(node).toRest()))
                is RoomsConcept -> broadcast(ChangeNotification(WhatChanged.ROOM_LIST, Rooms(node).toRest()))
                is LectureConcept -> broadcast(ChangeNotification(WhatChanged.LECTURE, Lecture(node).toRest()))
                is CoursesConcept -> broadcast(ChangeNotification(WhatChanged.LECTURE_LIST, Courses(node).toRest()))
                else -> logger.warn("Could not handle change")
            }
        }
    }

    private val sessions = ConcurrentSet<Session>()

    private fun broadcast(data: Any) {
        logger.info("Broadcasting to all sessions. [data={}]", data)
        val mapped = mapper.writeValueAsString(data)
        sessions.forEach { session ->
            logger.debug("Broadcasting to session. [session={}, mapped={}]", session, mapped)
            session.asyncRemote.sendObject(mapped)
        }
    }

    @OnOpen
    fun onOpen(session: Session) {
        logger.debug("Opening new session. [session={}]", session)
        sessions.add(session);
    }

    @OnClose
    fun onClose(session: Session) {
        logger.debug("Closing session regularly. [session={}]", session)
        sessions.remove(session)
    }

    @OnError
    fun onError(session: Session, throwable: Throwable) {
        logger.warn("Closing session after error. [session={}, throwable={}]", session, throwable, throwable)
        sessions.remove(session)
    }

}