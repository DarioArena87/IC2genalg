package com.gmail.dario.reactors.ui.reactorcommands.events;

import com.gmail.dario.reactors.ui.reactorcommands.ReactorCommands;
import com.vaadin.flow.component.ComponentEvent;

public class StopSimulationEvent extends ComponentEvent<ReactorCommands> {
    public StopSimulationEvent(ReactorCommands source) { super(source, false); }
}
