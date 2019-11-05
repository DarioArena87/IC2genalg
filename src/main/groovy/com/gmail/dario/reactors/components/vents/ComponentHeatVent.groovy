package com.gmail.dario.reactors.components.vents

import com.gmail.dario.reactors.components.HeatingObject
import groovy.transform.CompileStatic

import static com.google.common.collect.FluentIterable.from

@CompileStatic
class ComponentHeatVent extends Vent {

    @Override
    void tick() {
        from(connectedComponents).filter(HeatingObject).toList()*.removeHeat(4)
    }
}
