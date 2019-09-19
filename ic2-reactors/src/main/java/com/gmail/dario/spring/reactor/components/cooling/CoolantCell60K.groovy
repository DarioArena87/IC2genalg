package com.gmail.dario.spring.reactor.components.cooling

import com.gmail.dario.spring.reactor.components.HeatSink

class CoolantCell60K extends HeatSink {

    @Override
    void tick() {

    }

    @Override
    BigDecimal getMaxHeat() {
        return 60_000
    }

}
