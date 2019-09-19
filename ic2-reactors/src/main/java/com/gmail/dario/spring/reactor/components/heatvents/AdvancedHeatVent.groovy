package com.gmail.dario.spring.reactor.components.heatvents

import com.gmail.dario.spring.reactor.components.HeatSink

class AdvancedHeatVent extends HeatSink {

    @Override
    void tick() {
        dissipateHeat(12.0)
    }

    @Override
    BigDecimal getMaxHeat() {
        1000
    }

    @Override
    void addHeat(BigDecimal heat) {
        this.heat += heat
    }

}
