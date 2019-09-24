package com.gmail.dario.reactors.ui;

import com.gmail.dario.reactors.nulcearreactor.Reactor;
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

    Reactor reactor;
    
    ReactorGrid reactorGrid;

    ReactorCommands reactorCommands = new ReactorCommands();

    public ReactorSimulation() {

        add(new Div(new H2("Reactor components"), components));
        
        reactor = new Reactor(dimensionsChooser.getRows(), dimensionsChooser.getColumns());
        reactorGrid = new ReactorGrid(reactor);
        
        HorizontalLayout reactorLayout = new HorizontalLayout(reactorGrid, reactorCommands);
        reactorSimulatorLayout.add(dimensionsChooser, reactorLayout);
        add(reactorSimulatorLayout);

    }

}
