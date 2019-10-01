package com.gmail.dario.reactors.components.fuelcells

import com.gmail.dario.reactors.components.ReactorComponent
import com.gmail.dario.reactors.components.reflectors.Reflector
import com.google.common.collect.FluentIterable
import groovy.transform.CompileStatic

import static com.google.common.collect.FluentIterable.from

@CompileStatic
abstract class UraniumCell extends ReactorComponent {

    double pulsesLeft

    final double maxPulses

    double getDurabilityLeft() { pulsesLeft / maxPulses }

    abstract int getNumberOfRods()

    abstract int getEuGenerated()

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
        FluentIterable<UraniumCell> connectedUraniumCells = from(connectedComponents).filter(UraniumCell)
        if (!connectedUraniumCells.isEmpty()) {
            connectedUraniumCells.transform { it.numberOfRods }.sum() as int
        }
        else {
            0
        }
    }

    int getConnectedReflectors() {
        from(connectedComponents).filter(Reflector).size()
    }
}
