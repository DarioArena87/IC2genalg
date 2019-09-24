package com.gmail.dario.reactors.components.fuelcells

class DualUraniumCell extends UraniumCell {

    final BigDecimal numberOfRods = 2
    final BigDecimal euGenerated = 20

    DualUraniumCell() {
        super(400_000)
    }
}
