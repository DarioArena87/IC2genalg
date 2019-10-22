package com.gmail.dario.reactors.components.fuelcells

import groovy.transform.CompileStatic

@CompileStatic
class DualUraniumCell extends UraniumCell {

    final int numberOfRods = 2
    final int euGenerated = 20

    DualUraniumCell() {
        super(400_000)
    }
}
