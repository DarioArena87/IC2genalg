package com.gmail.dario.reactors.components.reflectors

import com.gmail.dario.reactors.components.ReactorComponent
import com.gmail.dario.reactors.components.fuelcells.UraniumCell
import com.google.common.collect.FluentIterable
import groovy.transform.CompileStatic

import static com.google.common.collect.FluentIterable.from

@CompileStatic
abstract class Reflector extends ReactorComponent {

    int numberOfPulses = 0

    abstract int getMaxPulses()

    @Override
    double getDurabilityLeft() {
        return 1 - numberOfPulses / maxPulses
    }

    @Override
    void tick() {
        FluentIterable<UraniumCell> connectedUraniumCells = from(connectedComponents).filter(UraniumCell)

        if (!connectedUraniumCells.empty) {
            numberOfPulses += connectedUraniumCells*.numberOfRods.sum() as int
        }
    }
}
