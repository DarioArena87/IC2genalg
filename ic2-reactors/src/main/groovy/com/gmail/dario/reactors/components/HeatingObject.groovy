package com.gmail.dario.reactors.components

import groovy.transform.CompileStatic

import static com.gmail.dario.reactors.utils.Bounder.bound

@CompileStatic
abstract class HeatingObject extends ReactorComponent {
    
    int heat = 0
    
    int removeHeat(int heatToGet) {
        int drawnHeat = bound heatToGet toAtMost heat
        heat -= drawnHeat
        return drawnHeat
    }
    
    void putHeat(int heatToPut) {
        heat += heatToPut
    }

    double getHeatPercentage() {
        heat / maxHeat
    }

    @Override
    double getDurabilityLeft() {
        1 - heatPercentage
    }

    abstract int getMaxHeat()
}
