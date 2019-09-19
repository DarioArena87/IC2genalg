package com.gmail.dario.spring.reactor.components.heatexchangers

class HeatExchanger extends HeatMover {

    @Override
    void tick() {
        exchangeHeatWithConnectedComponents(12.0)
        exchangeHeatWithVessel(4.0)
    }

    @Override
    BigDecimal getMaxHeat() {
        2_500
    }
}
