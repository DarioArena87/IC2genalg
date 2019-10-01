package com.gmail.dario.reactors.components.fuelcells

class QuadUraniumCell extends UraniumCell {

    final int numberOfRods = 4
    final BigDecimal euGenerated = 60

    QuadUraniumCell() {
        super(800_000)
    }
}
