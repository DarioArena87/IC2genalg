package com.gmail.dario.reactors.ui

import com.gmail.dario.reactors.components.ReactorComponent
import com.gmail.dario.reactors.nulcearreactor.Reactor
import com.gmail.dario.reactors.ui.events.DimensionChangeEvent
import com.gmail.dario.reactors.ui.events.StartSimulationEvent
import com.vaadin.flow.component.UI
import com.vaadin.flow.component.html.Div
import com.vaadin.flow.component.html.H2
import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.component.textfield.NumberField

import static java.util.stream.Collectors.toList

class ReactorSimulation extends HorizontalLayout {

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
            new ReactorSimulationThread(this.UI.get(), this, reactor, e.ticks).start()
        }
    }

    NumberField euGenerated = new NumberField(label: "EU", value: 0)

    NumberField heat = new NumberField(label: "Heat", value: 0)

    Reactor reactor = new Reactor(dimensionsChooser.rows, dimensionsChooser.columns)

    ReactorGrid reactorGrid = new ReactorGrid(reactor);

    ReactorSimulation() {

        add(
            new Div(new H2("Reactor components"), new ReactorComponentsAccordion())
        )

        add(
            new VerticalLayout(
                dimensionsChooser,
                new HorizontalLayout(reactorGrid, reactorCommands),
                new HorizontalLayout(euGenerated, heat)
            )
        )

    }

    private static class ReactorSimulationThread extends Thread {

        UI ui
        ReactorSimulation reactorSimulation
        Reactor reactor
        int ticks

        ReactorSimulationThread(UI ui, ReactorSimulation reactorSimulation, Reactor reactor, int ticks){
            this.ui = ui
            this.reactorSimulation = reactorSimulation
            this.reactor = reactor
            this.ticks = ticks
        }

        @Override
        void run() {
            for (int i = 0; i < ticks; i++) {
                reactor.tick();
                if (i % 20 == 0) {
                    updateUi()
                }
            }

            updateUi()
        }

        private updateUi(){
            ui.access {
                reactorSimulation.reactorGrid.update()
                reactorSimulation.euGenerated.value = reactor.eu
                reactorSimulation.heat.value = reactor.heat
            }
        }
    }

    private static void randomize(Reactor reactor) {
        List<ReactorComponent> randomComponents = new Random().ints(reactor.rows * reactor.columns, 0, ReactorComponentMapper.values().length)
                                                              .boxed()
                                                              .map { ReactorComponentMapper.values()[it] }
                                                              .map { it.create() }
                                                              .collect(toList());

        int k = 0;
        for (int i = 0; i < reactor.rows; i++) {
            for (int j = 0; j < reactor.columns; j++) {
                reactor.install(randomComponents[k++], i, j);
            }
        }

        reactor.connectComponents();
        reactor.exploded = false
    }

}
