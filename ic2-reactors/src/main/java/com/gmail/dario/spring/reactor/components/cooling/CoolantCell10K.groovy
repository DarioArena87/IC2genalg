package com.gmail.dario.spring.reactor.components.cooling

import com.gmail.dario.spring.reactor.components.HeatSink

class CoolantCell10K extends HeatSink {

    @Override
    void tick() {

    }

    @Override
    BigDecimal getMaxHeat() {
        return 10_000
    }

}
