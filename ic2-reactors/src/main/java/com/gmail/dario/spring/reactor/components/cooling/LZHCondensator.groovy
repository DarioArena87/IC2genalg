package com.gmail.dario.spring.reactor.components.cooling

import com.gmail.dario.spring.reactor.components.HeatSink

class LZHCondensator extends HeatSink {

    @Override
    void tick() {

    }

    @Override
    BigDecimal getMaxHeat() {
        return 100_000
    }

}
