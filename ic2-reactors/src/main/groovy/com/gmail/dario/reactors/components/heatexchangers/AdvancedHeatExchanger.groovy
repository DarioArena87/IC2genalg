package com.gmail.dario.reactors.components.heatexchangers

class AdvancedHeatExchanger extends HeatMover {

    final BigDecimal maxHeat = 10_000

    @Override
    void tick() {
        transferHeatWithConnectedComponents(24.0)
        transferHeatWithReactor(8.0)
    }
}
