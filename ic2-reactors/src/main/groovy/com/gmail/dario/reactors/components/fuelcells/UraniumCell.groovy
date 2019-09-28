package com.gmail.dario.reactors.components.fuelcells

import com.gmail.dario.reactors.components.ReactorComponent
import com.gmail.dario.reactors.components.reflectors.Reflector
import groovy.transform.CompileStatic

import static com.gmail.dario.reactors.utils.FastMath.sum

@CompileStatic
abstract class UraniumCell extends ReactorComponent {

    BigDecimal pulsesLeft

    final BigDecimal maxPulses

    BigDecimal getDurabilityLeft() { pulsesLeft / maxPulses }

    abstract BigDecimal getNumberOfRods()

    abstract BigDecimal getEuGenerated()

    UraniumCell(BigDecimal pulses) {
        this.pulsesLeft = pulses
        this.maxPulses = pulses
    }

    @Override
    void tick() {
        BigDecimal efficiency = 1 + connectedRods + connectedReflectors
        vessel.eu += euGenerated * efficiency
        vessel.putHeat(efficiency * (efficiency + 1) * 2)
        pulsesLeft--
    }


    BigDecimal getConnectedRods() {
        List<UraniumCell> connectedCells = connectedComponents.findAll { it in UraniumCell } as List<UraniumCell>
        sum(connectedCells*.numberOfRods)
    }

    BigDecimal getConnectedReflectors() {
        connectedComponents.findAll { it in Reflector }.size()
    }
}
