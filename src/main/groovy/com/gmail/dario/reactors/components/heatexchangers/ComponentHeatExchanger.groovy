package com.gmail.dario.reactors.components.heatexchangers

class ComponentHeatExchanger extends HeatMover {

    final int maxHeat = 5_000

    @Override
    void tick() {
        transferHeatWithConnectedComponents(36)
    }
}
