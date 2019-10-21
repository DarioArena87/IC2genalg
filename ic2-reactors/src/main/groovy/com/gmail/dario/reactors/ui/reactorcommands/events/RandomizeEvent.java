package com.gmail.dario.reactors.ui.reactorcommands.events;

import com.gmail.dario.reactors.ui.reactorcommands.ReactorCommands;
import com.vaadin.flow.component.ComponentEvent;

public class RandomizeEvent extends ComponentEvent<ReactorCommands> {
    public RandomizeEvent(final ReactorCommands source) {
        super(source, false);
    }
}
