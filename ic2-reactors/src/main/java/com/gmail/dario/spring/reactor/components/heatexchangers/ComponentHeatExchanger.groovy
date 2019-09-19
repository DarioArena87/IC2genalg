package com.gmail.dario.spring.reactor.components.heatexchangers

class ComponentHeatExchanger extends HeatMover {

    @Override
    void tick() {
        exchangeHeatWithConnectedComponents(36.0)
    }

    @Override
    BigDecimal getMaxHeat() {
        5_000
    }

}
