package com.gmail.dario.spring.reactor.components.uraniumcells


import com.gmail.dario.spring.reactor.components.Component
import com.gmail.dario.spring.reactor.components.reflectors.Reflector

import groovy.transform.CompileStatic

@CompileStatic
abstract class UraniumCell extends Component {

    @Override
    void tick() {
        vessel.acceptHeat(heat)
        heat = BigDecimal.ZERO
        BigDecimal surroundingUraniumCells = numberOfConnectedUraniumCells
        Collection<Component> connectedRefelectors = connectedComponents.findAll { it in Reflector }
        BigDecimal efficiency = 1 + surroundingUraniumCells + connectedRefelectors.size()
        vessel.eu += euGenerated * efficiency
        heat = efficiency * (efficiency + 1) * 2
    }

    BigDecimal getNumberOfConnectedUraniumCells() {
        Collection<Component> connectedUraniumCells = connectedComponents.findAll { it in UraniumCell }

        connectedUraniumCells.findAll { it in SingleUraniumCell }.size() +
        connectedUraniumCells.findAll { it in DualUraniumCell }.size() * 2.0 +
        connectedUraniumCells.findAll { it in QuadUraniumCell }.size() * 4.0
    }

    @Override
    BigDecimal getMaxHeat() {
        BigDecimal.valueOf(Long.MAX_VALUE)
    }


    abstract BigDecimal getEuGenerated()

}
