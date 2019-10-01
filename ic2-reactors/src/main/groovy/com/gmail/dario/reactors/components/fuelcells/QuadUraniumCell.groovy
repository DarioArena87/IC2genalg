package com.gmail.dario.reactors.components.fuelcells

import groovy.transform.CompileStatic

@CompileStatic
class QuadUraniumCell extends UraniumCell {

    final int numberOfRods = 4
    final int euGenerated = 60

    QuadUraniumCell() {
        super(800_000)
    }
}
