package com.gmail.dario.reactors.components.vents

import com.gmail.dario.reactors.components.HeatingObject
import com.gmail.dario.reactors.components.ReactorComponent
import groovy.transform.CompileStatic

import static com.gmail.dario.reactors.utils.Bounder.bound

@CompileStatic
abstract class Vent extends ReactorComponent implements HeatingObject {

    final int maxHeat = 1000

    void dissipateHeatFromReactor(int heatToGet) {
        heat += vessel.removeHeat(heatToGet)
    }

    void dissipateHeatToAir(int heatToDissipate) {
        heat -= bound heatToDissipate toAtMost heat
    }
}
