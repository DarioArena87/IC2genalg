package com.gmail.dario.spring.reactor.components.cooling

import com.gmail.dario.spring.reactor.components.HeatSink

class CoolantCell30K extends HeatSink {

    @Override
    void tick() {

    }

    @Override
    BigDecimal getMaxHeat() {
        return 30_000
    }

}
