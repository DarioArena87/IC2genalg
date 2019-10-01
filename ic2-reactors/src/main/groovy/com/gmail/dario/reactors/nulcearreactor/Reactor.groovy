package com.gmail.dario.reactors.nulcearreactor

import com.gmail.dario.reactors.components.EmptyCell
import com.gmail.dario.reactors.components.ReactorComponent
import com.gmail.dario.reactors.components.TickListener
import groovy.transform.CompileStatic

import static com.gmail.dario.reactors.utils.Bounder.bound

@CompileStatic
class Reactor implements TickListener {

    private static final EmptyCell EMPTY_CELL = new EmptyCell()

    final int maxHeat = 10_000
    int rows
    int columns

    List<List<ReactorComponent>> components

    int eu = 0

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
        boolean explodedComponent = false
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                ReactorComponent component = components[i][j]
                component.tick()
                if (component.durabilityLeft == 0) {
                    components[i][j] = EMPTY_CELL
                    explodedComponent = true
                }
            }
        }
        
        if (explodedComponent) {
            disconnectComponents()
            connectComponents()
        }
    }

    void disconnectComponents() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                components[i][j].connectedComponents.clear()
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

    int heat = 0

    int removeHeat(int heatToGet) {
        int drawnHeat = bound heatToGet toAtMost heat
        heat -= drawnHeat
        return drawnHeat
    }

    void putHeat(int heatToPut) {
        heat += heatToPut
    }

    double getHeatPercentage() {
        heat / maxHeat
    }

    double getDurabilityLeft() {
        1 - heatPercentage
    }
}
