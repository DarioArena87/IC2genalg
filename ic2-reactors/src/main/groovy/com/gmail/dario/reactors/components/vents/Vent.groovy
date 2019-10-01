package com.gmail.dario.reactors.components.vents

import com.gmail.dario.reactors.components.HeatingObject
import com.gmail.dario.reactors.components.ReactorComponent

import groovy.transform.CompileStatic

@CompileStatic
abstract class Vent extends HeatingObject {

    final int maxHeat = 1000

    void dissipateHeatFromReactor(int heat) {
        this.heat += vessel.removeHeat(heat)
    }

    void dissipateHeatToAir(int heat) {
        this.heat -= heat
    }
}
