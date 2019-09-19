package com.gmail.dario.spring.reactor.components.heatvents

import com.gmail.dario.spring.reactor.components.HeatSink

class ReactorHeatVent extends HeatSink {

    @Override
    void tick() {
        BigDecimal drawnHeat = vessel.drawnHeat(5)
        heat += drawnHeat
        dissipateHeat(5)
    }

    @Override
    BigDecimal getMaxHeat() {
        1000
    }

}
