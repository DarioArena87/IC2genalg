package com.gmail.dario.spring.reactor.components.uraniumcells

import com.gmail.dario.spring.reactor.components.Component
import com.gmail.dario.spring.reactor.components.reflectors.Reflector

class QuadUraniumCell extends UraniumCell {

    @Override
    void tick() {
        super.tick()
        Collection<Component> connectedRefelectors = connectedComponents.findAll { it in Reflector }
        connectedRefelectors.each { it.heat += 4 }
    }

    @Override
    BigDecimal getEuGenerated() {
        60
    }

}
