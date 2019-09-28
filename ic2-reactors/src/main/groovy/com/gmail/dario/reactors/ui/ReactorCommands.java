package com.gmail.dario.reactors.ui;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.shared.Registration;

public class ReactorCommands extends Composite<VerticalLayout> {

    private static final long serialVersionUID = 3105902612479543441L;

    final Button randomize = new Button("Randomize", e -> fireEvent(new RandomizeEvent(this, false)));

    public static class RandomizeEvent extends ComponentEvent<ReactorCommands> {
        private static final long serialVersionUID = -6762580615492057146L;

        public RandomizeEvent(final ReactorCommands source, final boolean fromClient) {
            super(source, fromClient);
        }
    }

    final Button simulate = new Button("Simulate");

    final Button evolve = new Button("Evolve");

    public ReactorCommands() {
        getContent().add(randomize, simulate, evolve);
    }

    public Registration setOnRandomize(final ComponentEventListener<RandomizeEvent> e) {
        return addListener(RandomizeEvent.class, e);
    }
}
