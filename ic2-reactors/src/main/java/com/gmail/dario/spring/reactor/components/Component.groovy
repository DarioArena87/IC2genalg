package com.gmail.dario.spring.reactor.components

import com.gmail.dario.spring.reactor.Reactor
import com.gmail.dario.spring.reactor.interfaces.TickListener

import groovy.transform.CompileStatic

@CompileStatic
abstract class Component implements TickListener {

    Reactor vessel
    Collection<Component> connectedComponents = []
    BigDecimal heat = 0

    void connect(Component component) {
        connectedComponents << component
    }

    BigDecimal dissipateHeat(BigDecimal heatToDissipate) {
        heat = Math.max(0, (heat - heatToDissipate).intValue())
    }

    void acceptHeat(BigDecimal heatToAccept) {
        heat += heatToAccept
    }

    BigDecimal drawnHeat(BigDecimal heatToDraw) {
        BigDecimal drawnHeat = Math.min(heat.intValue(), heatToDraw.intValue())
        heat -= drawnHeat
        drawnHeat
    }

    abstract BigDecimal getMaxHeat()
}
