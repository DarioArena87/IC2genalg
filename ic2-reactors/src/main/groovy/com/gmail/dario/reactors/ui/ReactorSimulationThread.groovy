package com.gmail.dario.reactors.ui

import com.gmail.dario.reactors.nulcearreactor.Reactor
import com.vaadin.flow.component.UI

class ReactorSimulationThread extends Thread {
    public static final int UPDATE_INTERVAL = 250
    UI ui
    ReactorSimulation reactorSimulation
    Reactor reactor
    int ticks
    boolean terminate

    ReactorSimulationThread(UI ui, ReactorSimulation reactorSimulation, Reactor reactor, int ticks) {
        this.ui = ui
        this.reactorSimulation = reactorSimulation
        this.reactor = reactor
        this.ticks = ticks
    }

    @Override
    void run() {
        for (int i = 0; i < ticks && !terminate; i++) {
            reactor.tick();
            if (i % UPDATE_INTERVAL == 0) {
                updateUi()
            }
        }

        updateUi()
    }

    private updateUi() {
        ui.access {
            reactorSimulation.reactorGrid.update()
            reactorSimulation.euGenerated.value = reactor.eu
            reactorSimulation.heat.value = reactor.heat
        }
    }
}