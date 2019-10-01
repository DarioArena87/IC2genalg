package com.gmail.dario.reactors.components.fuelcells

class DualUraniumCell extends UraniumCell {

    final int numberOfRods = 2
    final BigDecimal euGenerated = 20

    DualUraniumCell() {
        super(400_000)
    }
}
