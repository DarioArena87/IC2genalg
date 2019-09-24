package com.gmail.dario.reactors.ui;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class ReactorCommands extends Composite<VerticalLayout> {

    private static final long serialVersionUID = 3105902612479543441L;

    final Button randomize = new Button("Randomize");

    final Button simulate = new Button("Simulate");

    final Button evolve = new Button("Evolve");

    public ReactorCommands() {
        getContent().add(randomize, simulate, evolve);
    }
}
