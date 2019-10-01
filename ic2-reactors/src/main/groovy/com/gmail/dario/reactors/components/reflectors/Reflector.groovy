package com.gmail.dario.reactors.components.reflectors

import com.gmail.dario.reactors.components.ReactorComponent
import com.gmail.dario.reactors.components.fuelcells.UraniumCell

abstract class Reflector extends ReactorComponent {

    int numberOfPulses = 0

    abstract BigDecimal getMaxPulses()

    @Override
    double getDurabilityLeft() {
        return 1 - numberOfPulses / maxPulses
    }

    @Override
    void tick() {
        List<UraniumCell> connectedCells = connectedComponents.findAll { it in UraniumCell } as List<UraniumCell>
        if (connectedCells) {
            numberOfPulses += connectedCells*.numberOfRods.sum()
        }
    }
}
