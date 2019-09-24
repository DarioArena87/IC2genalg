package com.gmail.dario.reactors.components.heatexchangers

class ComponentHeatExchanger extends HeatMover {

    final BigDecimal maxHeat = 5_000

    @Override
    void tick() {
        transferHeatWithConnectedComponents(36.0)
    }
}
