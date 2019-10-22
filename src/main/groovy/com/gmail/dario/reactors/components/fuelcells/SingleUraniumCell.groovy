package com.gmail.dario.reactors.components.fuelcells

import groovy.transform.CompileStatic

@CompileStatic
class SingleUraniumCell extends UraniumCell {

    final int numberOfRods = 1
    final int euGenerated = 5

    SingleUraniumCell() {
        super(200_000)
    }
}
