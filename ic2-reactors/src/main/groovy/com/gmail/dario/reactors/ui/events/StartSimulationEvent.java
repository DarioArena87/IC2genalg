package com.gmail.dario.reactors.ui.events;

import com.gmail.dario.reactors.ui.ReactorCommands;
import com.vaadin.flow.component.ComponentEvent;

public class StartSimulationEvent extends ComponentEvent<ReactorCommands> {
    public StartSimulationEvent(ReactorCommands source, int ticks) {
        super(source, false);
        this.ticks = ticks;
    }

    public StartSimulationEvent(ReactorCommands source) {
        this(source, 6000);
    }

    public final int getTicks() {
        return ticks;
    }

    private final int ticks;
}
