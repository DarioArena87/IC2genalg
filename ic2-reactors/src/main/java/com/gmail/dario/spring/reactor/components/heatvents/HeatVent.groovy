package com.gmail.dario.spring.reactor.components.heatvents

import com.gmail.dario.spring.reactor.components.HeatSink

class HeatVent extends HeatSink {

    @Override
    void tick() {
        dissipateHeat(6)
    }

    @Override
    BigDecimal getMaxHeat() {
        1000
    }

}
