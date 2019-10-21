package com.gmail.dario.reactors.ui.reactorcommands.events;

import com.gmail.dario.reactors.ui.reactorcommands.ReactorCommands;
import com.vaadin.flow.component.ComponentEvent;

public class StartEvolutionEvent extends ComponentEvent<ReactorCommands> {

    private final int simulationTicks;

    public StartEvolutionEvent(ReactorCommands source, int simulationTicks) {
        super(source, false);
        this.simulationTicks = simulationTicks;
    }

    public int getSimulationTicks() {
        return simulationTicks;
    }
}
