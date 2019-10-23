package com.gmail.dario.reactors.ui.reactorcommands.events;

import com.gmail.dario.reactors.ui.reactorcommands.ReactorCommands;
import com.vaadin.flow.component.ComponentEvent;

public class RewindEvent extends ComponentEvent<ReactorCommands> {
    public RewindEvent(ReactorCommands source) {
        super(source, false);
    }
}
