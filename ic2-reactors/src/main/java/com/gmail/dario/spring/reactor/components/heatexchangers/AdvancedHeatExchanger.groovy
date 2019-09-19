package com.gmail.dario.spring.reactor.components.heatexchangers

class AdvancedHeatExchanger extends HeatMover {

    @Override
    void tick() {
        exchangeHeatWithConnectedComponents(24.0)
        exchangeHeatWithVessel(8.0)
    }

    @Override
    BigDecimal getMaxHeat() {
        10_000
    }

}
