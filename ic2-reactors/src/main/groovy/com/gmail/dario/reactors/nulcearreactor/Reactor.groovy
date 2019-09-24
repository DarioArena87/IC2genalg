package com.gmail.dario.reactors.nulcearreactor

import com.gmail.dario.reactors.components.EmptyCell
import com.gmail.dario.reactors.components.ReactorComponent
import com.gmail.dario.reactors.components.TickListener

import static com.gmail.dario.reactors.utils.Bounder.bound

class Reactor implements TickListener {

    private static final EMPTY_CELL = new EmptyCell()

    final BigDecimal maxHeat = 10_000
    int rows
    int columns

    List<List<ReactorComponent>> components

    BigDecimal eu = 0

    Reactor(int rows = 0, int columns = 0) {
        setDimensions(rows, columns)
    }

    void setDimensions(int rows, int columns) {
        this.rows = rows
        this.columns = columns
        components = new ArrayList<>(rows)
        for (int i = 0; i < rows; i++) {
            components[i] = new ArrayList<ReactorComponent>(columns)
            for (int j = 0; j < columns; j++) {
                components[i][j] = EMPTY_CELL
            }
        }
    }

    void install(ReactorComponent component, int row, int column) {
        components[row][column] = component
        component.vessel = this
    }

    @Override
    void tick() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                components[i][j].tick()
            }
        }
    }

    void connectComponents() {
        for (int i = 0; i < rows - 1; i++) {
            for (int j = 0; j < columns - 1; j++) {
                ReactorComponent component = components[i][j]
                if (component.is(EMPTY_CELL)) {
                    continue
                }
                def surroundingComponents = [components[i + 1][j], components[i][j + 1]].findAll { !it.is(EMPTY_CELL) }
                if (!surroundingComponents.empty) {
                    component.connectedComponents += surroundingComponents
                    surroundingComponents.each {it.connectedComponents += component }
                }
            }
        }
    }

    BigDecimal heat = 0

    BigDecimal removeHeat(BigDecimal heatToGet) {
        BigDecimal drawnHeat = bound heatToGet toAtMost heat
        heat -= drawnHeat
        return drawnHeat
    }

    void putHeat(BigDecimal heatToPut) {
        heat += heatToPut
    }

    BigDecimal getHeatPercentage() {
        heat / maxHeat
    }

    BigDecimal getDurabilityLeft() {
        1 - heatPercentage
    }
}
