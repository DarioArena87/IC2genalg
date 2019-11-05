package com.gmail.dario.reactors.ui.reactorcommands.events

import com.gmail.dario.reactors.ui.reactorcommands.ReactorCommands
import com.vaadin.flow.component.ComponentEvent

class StartEvolutionEvent extends ComponentEvent<ReactorCommands> {

    final int simulationTicks

    StartEvolutionEvent(ReactorCommands source, int simulationTicks) {
        super(source, false)
        this.simulationTicks = simulationTicks
    }
}
