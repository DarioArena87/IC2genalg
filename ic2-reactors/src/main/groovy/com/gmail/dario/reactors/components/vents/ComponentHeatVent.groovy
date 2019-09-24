package com.gmail.dario.reactors.components.vents

import com.gmail.dario.reactors.components.HeatingObject

import groovy.transform.CompileStatic

@CompileStatic
class ComponentHeatVent extends Vent {

    @Override
    void tick() {
        List<HeatingObject> heatingConnectedComponents = connectedComponents.findAll { it in HeatingObject } as List<HeatingObject>
        heatingConnectedComponents*.removeHeat(4.0)
    }
}
