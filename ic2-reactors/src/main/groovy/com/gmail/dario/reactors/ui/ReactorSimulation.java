package com.gmail.dario.reactors.ui;

import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class ReactorSimulation extends HorizontalLayout {

    private static final long serialVersionUID = -8525947343357750120L;

    Accordion components = new ReactorComponentsAccordion();
    
    DimensionsChooser dimensionsChooser = new DimensionsChooser();

    VerticalLayout reactorSimulatorLayout = new VerticalLayout();

    Div reactorGrid = new Div();

    ReactorCommands reactorCommands = new ReactorCommands();

    public ReactorSimulation() {

        add(new Div(new H2("Reactor components"), components));
        
        Div reactorLayout = new Div(reactorGrid, reactorCommands);
        reactorSimulatorLayout.add(dimensionsChooser, reactorLayout);
        add(reactorSimulatorLayout);

    }

}
