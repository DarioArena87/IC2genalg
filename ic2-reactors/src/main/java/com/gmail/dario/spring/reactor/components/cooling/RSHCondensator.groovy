package com.gmail.dario.spring.reactor.components.cooling

import com.gmail.dario.spring.reactor.components.HeatSink

class RSHCondensator extends HeatSink {

    @Override
    void tick() {

    }

    @Override
    BigDecimal getMaxHeat() {
        20_000
    }

}
