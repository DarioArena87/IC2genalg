package com.gmail.dario.spring.reactor.components.heatvents

import com.gmail.dario.spring.reactor.components.Component

class ComponentHeatVent extends Component {

    @Override
    void tick() {
        connectedComponents.each { it.dissipateHeat(4) }
    }

    @Override
    BigDecimal getMaxHeat() {
        1000
    }

}
