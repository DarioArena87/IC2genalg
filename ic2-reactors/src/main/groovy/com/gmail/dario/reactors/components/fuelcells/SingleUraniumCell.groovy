package com.gmail.dario.reactors.components.fuelcells

class SingleUraniumCell extends UraniumCell {

    final int numberOfRods = 1
    final BigDecimal euGenerated = 5

    SingleUraniumCell() {
        super(200_000)
    }
}
