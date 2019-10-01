package com.gmail.dario.reactors.components.fuelcells

import com.gmail.dario.reactors.components.ReactorComponent
import com.gmail.dario.reactors.components.reflectors.Reflector
import groovy.transform.CompileStatic

import static com.gmail.dario.reactors.utils.FastMath.sum

@CompileStatic
abstract class UraniumCell extends ReactorComponent {

    double pulsesLeft

    final double maxPulses

    double getDurabilityLeft() { pulsesLeft / maxPulses }

    abstract int getNumberOfRods()

    abstract BigDecimal getEuGenerated()

    UraniumCell(double pulses) {
        this.pulsesLeft = pulses
        this.maxPulses = pulses
    }

    @Override
    void tick() {
        int efficiency = 1 + connectedRods + connectedReflectors
        vessel.eu += euGenerated * efficiency
        vessel.putHeat(efficiency * (efficiency + 1) * 2)
        pulsesLeft--
    }


    int getConnectedRods() {
        List<UraniumCell> connectedCells = connectedComponents.findAll { it in UraniumCell } as List<UraniumCell>
        if (connectedCells) {
            (int) connectedCells*.numberOfRods.sum()
        }
        else {
            0
        }
    }

    int getConnectedReflectors() {
        connectedComponents.findAll { it in Reflector }.size()
    }
}
