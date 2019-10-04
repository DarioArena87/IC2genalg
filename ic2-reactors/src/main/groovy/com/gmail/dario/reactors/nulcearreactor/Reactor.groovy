package com.gmail.dario.reactors.nulcearreactor

import com.gmail.dario.reactors.components.EmptyCell
import com.gmail.dario.reactors.components.ReactorComponent
import com.gmail.dario.reactors.components.TickListener
import com.gmail.dario.reactors.components.platings.Plating
import groovy.transform.CompileStatic

import static com.gmail.dario.reactors.utils.Bounder.bound
import static com.google.common.collect.FluentIterable.from

@CompileStatic
class Reactor implements TickListener {

    private static final EmptyCell EMPTY_CELL = new EmptyCell()

    int maxHeat = 10_000
    boolean exploded
    int rows
    int columns

    List<List<ReactorComponent>> components

    int heat = 0
    int eu

    Reactor(int rows = 0, int columns = 0) {
        setDimensions(rows, columns)
    }

    void setDimensions(int rows, int columns) {
        this.eu = 0
        this.heat = 0

        this.exploded = false
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
        if (exploded) {
            return
        }

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

        exploded = heat >= maxHeat
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

        maxHeat = 10_000 + platingsHeatResistance
    }

    private int getPlatingsHeatResistance() {
        def heatResistances = from(components.flatten()).filter(Plating)*.heatResistance
        if(!heatResistances) {
            0
        }
        else {
            heatResistances.sum() as int
        }
    }

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
