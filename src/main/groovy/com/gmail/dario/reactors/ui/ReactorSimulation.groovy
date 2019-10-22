package com.gmail.dario.reactors.ui

import com.gmail.dario.reactors.nulcearreactor.Reactor
import com.gmail.dario.reactors.ui.dimensionschooser.DimensionChangeEvent
import com.gmail.dario.reactors.ui.dimensionschooser.DimensionsChooser
import com.gmail.dario.reactors.ui.reactorcommands.ReactorCommands
import com.gmail.dario.reactors.ui.reactorcommands.events.StartEvolutionEvent
import com.gmail.dario.reactors.ui.reactorcommands.events.StartSimulationEvent
import com.vaadin.flow.component.html.Div
import com.vaadin.flow.component.html.H2
import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.component.textfield.NumberField

class ReactorSimulation extends HorizontalLayout {

    ReactorSimulationThread reactorSimulationThread

    ReactorEvolutionThread reactorEvolutionThread

    DimensionsChooser dimensionsChooser = new DimensionsChooser().tap {
        onDimensionChange = { DimensionChangeEvent e ->
            reactor = Reactor.builder(dimensionsChooser.rows, dimensionsChooser.columns).empty()
            reactorGrid.reactor = reactor
            reactorGrid.update()
            euGenerated.value = reactor.eu
            heat.value = reactor.heat
        }
    }

    ReactorCommands reactorCommands = new ReactorCommands().tap {
        onRandomize = {
            reactor = Reactor.builder(dimensionsChooser.rows, dimensionsChooser.columns).random()
            reactorGrid.reactor = reactor
            euGenerated.value = 0
            heat.value = 0
            reactorGrid.update()
        }

        onSimulate = { StartSimulationEvent e ->
            reactorSimulationThread = new ReactorSimulationThread(UI.get(), this, reactor, e.ticks)
            reactorSimulationThread.start()
        }

        onStopSimulation = {
            if (reactorSimulationThread) {
                reactorSimulationThread.terminate = true
            }
            reactorSimulationThread = null;
        }

        onStartEvolution = { StartEvolutionEvent e ->
            reactorEvolutionThread = new ReactorEvolutionThread(UI.get(), this, reactor, e.simulationTicks)
            reactorEvolutionThread.start()
        }
    }

    NumberField euGenerated = new NumberField(label: "EU", value: 0)

    NumberField heat = new NumberField(label: "Heat", value: 0)

    Reactor reactor = new Reactor(dimensionsChooser.rows, dimensionsChooser.columns)

    ReactorGrid reactorGrid = new ReactorGrid(reactor: reactor);

    ReactorSimulation() {

        add (
            new Div(new H2("Reactor components"), new ReactorComponentsAccordion())
        )

        add (
            new VerticalLayout(
                dimensionsChooser,
                new HorizontalLayout(reactorGrid, reactorCommands),
                new HorizontalLayout(euGenerated, heat)
            )
        )

    }

}
