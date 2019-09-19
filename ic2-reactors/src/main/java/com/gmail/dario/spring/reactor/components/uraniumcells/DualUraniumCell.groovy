package com.gmail.dario.spring.reactor.components.uraniumcells

import com.gmail.dario.spring.reactor.components.Component
import com.gmail.dario.spring.reactor.components.reflectors.Reflector

class DualUraniumCell extends UraniumCell {

    @Override
    void tick() {
        super.tick()
        Collection<Component> connectedRefelectors = connectedComponents.findAll { it in Reflector }
        connectedRefelectors.each { it.heat += 2 }
    }

    @Override
    BigDecimal getEuGenerated() {
        20
    }

}
