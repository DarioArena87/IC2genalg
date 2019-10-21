package com.gmail.dario.reactors.components.heatexchangers

import groovy.transform.CompileStatic

@CompileStatic
class AdvancedHeatExchanger extends HeatMover {

    final int maxHeat = 10_000

    @Override
    void tick() {
        transferHeatWithConnectedComponents(24)
        transferHeatWithReactor(8)
    }
}
