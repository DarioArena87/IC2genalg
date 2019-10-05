package com.gmail.dario.reactors.ui


import com.gmail.dario.reactors.nulcearreactor.Reactor
import com.gmail.dario.reactors.ui.dimensionschooser.DimensionChangeEvent
import com.gmail.dario.reactors.ui.dimensionschooser.DimensionsChooser
import com.gmail.dario.reactors.ui.reactorcommands.ReactorCommands
import com.gmail.dario.reactors.ui.reactorcommands.events.StartSimulationEvent
import com.vaadin.flow.component.html.Div
import com.vaadin.flow.component.html.H2
import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.component.textfield.NumberField

import static java.util.stream.Collectors.toList

class ReactorSimulation extends HorizontalLayout {

    ReactorSimulationThread reactorSimulationThread;

    DimensionsChooser dimensionsChooser = new DimensionsChooser().tap {
        onDimensionChange = { DimensionChangeEvent e ->
            reactor.setDimensions(e.newRows, e.newColumns)
            reactorGrid.update()
            euGenerated.value = reactor.eu
            heat.value = reactor.heat
        }
    }

    ReactorCommands reactorCommands = new ReactorCommands().tap {
        onRandomize = {
            randomize(reactor)
            euGenerated.value = 0
            heat.value = 0
            reactorGrid.update()
        }

        onSimulate = { StartSimulationEvent e ->
            reactorSimulationThread = new ReactorSimulationThread(this.UI.get(), this, reactor, e.ticks)
            reactorSimulationThread.start()
        }

        onStopSimulation = {
            if (reactorSimulationThread) {
                reactorSimulationThread.terminate = true
            }
            reactorSimulationThread = null;
        }
    }

    NumberField euGenerated = new NumberField(label: "EU", value: 0)

    NumberField heat = new NumberField(label: "Heat", value: 0)

    Reactor reactor = new Reactor(dimensionsChooser.rows, dimensionsChooser.columns)

    ReactorGrid reactorGrid = new ReactorGrid(reactor);

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

    private static void randomize(Reactor reactor) {
        def randomComponents = new Random().ints(reactor.rows * reactor.columns, 0, ReactorComponentMapper.values().length)
                                           .boxed()
                                           .map { ReactorComponentMapper.values()[it] }
                                           .map { it.create() }
                                           .collect(toList())

        int k = 0;
        for (int i = 0; i < reactor.rows; i++) {
            for (int j = 0; j < reactor.columns; j++) {
                reactor.install(randomComponents[k++], i, j)
            }
        }

        reactor.connectComponents();
        reactor.exploded = false
    }

}
