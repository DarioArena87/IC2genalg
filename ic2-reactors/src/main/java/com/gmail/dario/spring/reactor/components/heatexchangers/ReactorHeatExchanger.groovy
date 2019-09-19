package com.gmail.dario.spring.reactor.components.heatexchangers

class ReactorHeatExchanger extends HeatMover {

    @Override
    void tick() {
        exchangeHeatWithVessel(72)
    }

    @Override
    BigDecimal getMaxHeat() {
        5_000
    }

}
