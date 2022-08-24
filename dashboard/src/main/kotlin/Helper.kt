package org.modelix.sample.dashboard

import University.Schedule.structure.OneOff
import University.Schedule.structure.Recurring
import University.Schedule.structure.Schedule
import jetbrains.mps.lang.core.structure.INamedConcept
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toLocalDateTime

val INamedConcept.safeName
    get() = this.properties.name ?: "<no name>"


fun Schedule.toDate() : LocalDateTime {
    val date = this.children.at.properties.date
    val time = this.children.at.properties.time
    return try {
        """${date}T${time}""".toLocalDateTime()
    } catch (_: Exception) {
        LocalDateTime(0, 1,1,0,0)
    }
}
fun Schedule.readable(): String {
    return when(this) {
        is OneOff -> this.toDate().toString()
        is Recurring -> this.toDate().toString()
        else -> """Unknown schedule: ${this::class.java}"""
    }
}
