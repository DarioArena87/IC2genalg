package com.gmail.dario.spring.reactor.components.heatvents

import com.gmail.dario.spring.reactor.components.HeatSink

class OverclockedHeatVent extends HeatSink {

    @Override
    void tick() {
        BigDecimal drawnHeat = vessel.drawnHeat(36)
        heat += drawnHeat
        dissipateHeat(20)
    }

    @Override
    BigDecimal getMaxHeat() {
        1000
    }



}
