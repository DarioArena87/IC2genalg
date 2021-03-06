package com.gmail.dario.reactors.nulcearreactor

import com.gmail.dario.reactors.components.EmptyCell
import com.gmail.dario.reactors.components.HeatingObject
import com.gmail.dario.reactors.components.ReactorComponent
import com.gmail.dario.reactors.components.TickListener
import com.gmail.dario.reactors.components.platings.Plating
import com.gmail.dario.reactors.ui.ReactorComponentMapper
import groovy.transform.CompileDynamic
import groovy.transform.CompileStatic
import sun.tools.jar.CommandLine

import java.util.stream.Stream

import static com.gmail.dario.reactors.utils.Bounder.bound
import static com.google.common.collect.FluentIterable.from
import static java.util.stream.Collectors.toList

@CompileStatic
class Reactor implements HeatingObject, TickListener {

    private static final EmptyCell EMPTY_CELL = new EmptyCell()

    int maxHeat = 10_000
    boolean exploded
    int rows
    int columns

    List<List<ReactorComponent>> components

    int eu

    private Reactor(int rows = 0, int columns = 0) {
        this.eu = 0
        this.heat = 0
        this.exploded = false
        this.rows = rows
        this.columns = columns
        components = new ArrayList<>(rows)
        for (int i = 0; i < rows; i++) {
            components[i] = new ArrayList<ReactorComponent>(columns)
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
                if (component.durabilityLeft <= 0) {
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
                    surroundingComponents.each { it.connectedComponents += component }
                }
            }
        }

        maxHeat = 10_000 + platingsHeatResistance
    }

    private int getPlatingsHeatResistance() {
        def heatResistances = from(components.flatten()).filter(Plating)*.heatResistance
        if (!heatResistances) {
            0
        }
        else {
            heatResistances.sum() as int
        }
    }

    static Builder builder(int rows = 0, int columns = 0) {
        new Builder(rows, columns)
    }

    static class Builder {
        private final int rows
        private final int columns

        Builder(int rows, int columns) {
            this.rows = rows
            this.columns = columns
        }

        Reactor random() {
            List<Integer> componentList = new Random().ints(rows * columns, 0, ReactorComponentMapper.values().length)
                                                      .boxed()
                                                      .collect(toList())
            fromComponentIds(componentList)
        }

        @CompileDynamic
        Reactor fromComponentIds(List<Integer> componentIds) {
            def components = (0..<rows * columns).collect { componentIds[it] }
                                                 .collect(ReactorComponentMapper.&fromComponentId)
                                                 .collect { it.create() }
            fromComponents(components)
        }

        Reactor fromComponents(List<ReactorComponent> components) {
            new Reactor(rows, columns).tap {
                int k = 0
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < columns; j++) {
                        install(components[k++], i, j)
                    }
                }
                connectComponents()
            }
        }

        Reactor empty() {
            new Reactor(rows, columns).tap {
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < columns; j++) {
                        components[i][j] = EMPTY_CELL
                    }
                }
            }
        }
    }
}
