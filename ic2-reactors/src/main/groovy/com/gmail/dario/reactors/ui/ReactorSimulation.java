package com.gmail.dario.reactors.ui;

import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class ReactorSimulation extends HorizontalLayout {

    private static final long serialVersionUID = -8525947343357750120L;

    Accordion components = new ReactorComponentsAccordion();
    
    DimensionsChooser dimensionsChooser;

    VerticalLayout reactorSimulatorLayout = new VerticalLayout();

    ReactorGrid reactorGrid;

    ReactorCommands reactorCommands;

    public ReactorSimulation() {

        add(new Div(new H2("Reactor components"), components));
        
        dimensionsChooser = new DimensionsChooser();
        dimensionsChooser.setOnDimensionChange(e -> {
            reactorGrid.setDimensions(e.getNewRows(), e.getNewColumns());
        });
        
        reactorGrid = new ReactorGrid(dimensionsChooser.getRows(), dimensionsChooser.getColumns());
        
        reactorCommands = new ReactorCommands();
        reactorCommands.setOnRandomize(e -> {
            reactorGrid.randomize();
        });
        
        HorizontalLayout reactorLayout = new HorizontalLayout(reactorGrid, reactorCommands);
        reactorSimulatorLayout.add(dimensionsChooser, reactorLayout);
        add(reactorSimulatorLayout);

    }

}
