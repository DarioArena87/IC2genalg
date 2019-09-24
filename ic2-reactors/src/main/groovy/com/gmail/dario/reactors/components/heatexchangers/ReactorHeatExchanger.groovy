package com.gmail.dario.reactors.components.heatexchangers

class ReactorHeatExchanger extends HeatMover {

    final BigDecimal maxHeat = 5_000

    @Override
    void tick() {
        transferHeatWithReactor(72.0)
    }
}
