package com.gmail.dario.reactors.components.heatexchangers

class AdvancedHeatExchanger extends HeatMover {

    final int maxHeat = 10_000

    @Override
    void tick() {
        transferHeatWithConnectedComponents(24)
        transferHeatWithReactor(8)
    }
}
