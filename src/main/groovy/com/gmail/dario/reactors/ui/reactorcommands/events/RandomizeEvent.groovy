package com.gmail.dario.reactors.ui.reactorcommands.events

import com.gmail.dario.reactors.ui.reactorcommands.ReactorCommands
import com.vaadin.flow.component.ComponentEvent

class RandomizeEvent extends ComponentEvent<ReactorCommands> {
    RandomizeEvent(ReactorCommands source) {
        super(source, false)
    }
}
