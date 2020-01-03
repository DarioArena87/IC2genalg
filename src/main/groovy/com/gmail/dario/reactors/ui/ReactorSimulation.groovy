package com.gmail.dario.reactors.ui

import com.gmail.dario.reactors.nulcearreactor.Reactor
import com.gmail.dario.reactors.ui.dimensionschooser.DimensionChangeEvent
import com.gmail.dario.reactors.ui.dimensionschooser.DimensionsChooser
import com.gmail.dario.reactors.ui.reactorcommands.ReactorCommands
import com.gmail.dario.reactors.ui.reactorcommands.events.StartEvolutionEvent
import com.gmail.dario.reactors.ui.reactorcommands.events.StartSimulationEvent
import com.vaadin.flow.component.html.Div
import com.vaadin.flow.component.html.H2
import com.vaadin.flow.component.html.Label
import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.component.textfield.NumberField

import static java.util.stream.Collectors.toList

class ReactorSimulation extends HorizontalLayout {

    ReactorSimulationThread reactorSimulationThread

    ReactorEvolutionThread reactorEvolutionThread

    DimensionsChooser dimensionsChooser = new DimensionsChooser().tap {
        onDimensionChange = { DimensionChangeEvent e ->
            reactor = Reactor.builder(dimensionsChooser.rows, dimensionsChooser.columns).empty()
            reactorGrid.reactor = reactor
            euGenerated.value = reactor.eu
            heat.value = reactor.heat
            maxHeat.value = reactor.maxHeat
            componentList = [].withDefault { ReactorComponentMapper.EMPTY_CELL.id }
        }
    }

    ReactorCommands reactorCommands = new ReactorCommands().tap {
        onRandomize = {
            componentList = nRandomComponentIds(dimensionsChooser.rows * dimensionsChooser.columns)
            initializeSimulation()
        }

        onSimulate = { StartSimulationEvent e ->
            reactorSimulationThread = new ReactorSimulationThread(UI.get(), this, reactor, e.ticks)
            reactorSimulationThread.start()
        }

        onStopSimulation = {
            reactorSimulationThread?.terminate = true
        }

        onRewind = {
            initializeSimulation()
        }

        onStartEvolution = { StartEvolutionEvent e ->
            reactorEvolutionThread = new ReactorEvolutionThread(UI.get(), this, reactor, e.simulationTicks)
            reactorEvolutionThread.start()
        }
    }

    NumberField euGenerated = new NumberField(label: "EU", value: 0)

    NumberField heat = new NumberField(label: "Heat", value: 0)

    NumberField maxHeat = new NumberField(label: "Max Heat", enabled: false)

    List<Integer> componentList = [].withDefault { ReactorComponentMapper.EMPTY_CELL.id }

    Reactor reactor = Reactor.builder(dimensionsChooser.rows, dimensionsChooser.columns).empty()

    ReactorGrid reactorGrid = new ReactorGrid(reactor: reactor).tap {
        onComponentInstalled = { InstallComponentEvent e ->
            componentList[reactor.columns * e.row + e.column] = e.componentId
        }
    }

    ReactorSimulation() {
        add(
            new Div(
                new H2("Reactor components"),
                new ReactorComponentsAccordion()
            ),

            new VerticalLayout(
                dimensionsChooser,
                new HorizontalLayout(reactorGrid, reactorCommands),
                new HorizontalLayout(euGenerated, heat, maxHeat)
            )
        )
    }

    void initializeSimulation() {
        reactor = Reactor.builder(dimensionsChooser.rows, dimensionsChooser.columns).fromComponentIds(componentList)
        reactorGrid.reactor = reactor
        euGenerated.value = 0
        heat.value = 0
        maxHeat.value = reactor.maxHeat
    }

    private static List<Integer> nRandomComponentIds(int number) {
        new Random().ints(number, 0, ReactorComponentMapper.values().length).boxed().collect toList()
    }
}
