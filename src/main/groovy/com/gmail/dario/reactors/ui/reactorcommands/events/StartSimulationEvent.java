package com.gmail.dario.reactors.ui.reactorcommands.events;

import com.gmail.dario.reactors.ui.reactorcommands.ReactorCommands;
import com.vaadin.flow.component.ComponentEvent;

public class StartSimulationEvent extends ComponentEvent<ReactorCommands> {
    public StartSimulationEvent(ReactorCommands source, int ticks) {
        super(source, false);
        this.ticks = ticks;
    }

    public final int getTicks() {
        return ticks;
    }

    private final int ticks;
}
