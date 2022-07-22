package org.modelix.sample.dashboard

import jetbrains.mps.lang.core.structure.INamedConcept

val INamedConcept.safeName
    get() = this.properties.name ?: "<no name>"
