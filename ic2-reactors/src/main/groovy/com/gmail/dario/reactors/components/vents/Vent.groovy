package com.gmail.dario.reactors.components.vents

import com.gmail.dario.reactors.components.HeatingObject
import com.gmail.dario.reactors.components.ReactorComponent

import groovy.transform.CompileStatic

abstract class Vent extends HeatingObject {
    
    final BigDecimal maxHeat = 1000
    
    void dissipateHeatFromReactor(BigDecimal heat) {
        this.heat += vessel.removeHeat(heat)
    }
    
    void dissipateHeatToAir(BigDecimal heat) {
        this.heat -=heat
    }
}
