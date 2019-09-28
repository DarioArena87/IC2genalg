package com.gmail.dario.reactors.ui;

import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.component.textfield.NumberField;

class ReactorSimulation extends HorizontalLayout {

    private static final long serialVersionUID = -8525947343357750120L;

    Accordion components = new ReactorComponentsAccordion();
    
    DimensionsChooser dimensionsChooser

    VerticalLayout reactorSimulatorLayout = new VerticalLayout()

    ReactorGrid reactorGrid

    ReactorCommands reactorCommands

    NumberField euGenerated = new NumberField("EU").tap {
        value = 0
    }

    NumberField heat = new NumberField("Heat").tap {
        value = 0
    }

    ReactorSimulation() {

        add(new Div(new H2("Reactor components"), components));
        
        dimensionsChooser = new DimensionsChooser(onDimensionChange: {reactorGrid.setDimensions(it.newRows, it.newColumns)});

        reactorGrid = new ReactorGrid(dimensionsChooser.getRows(), dimensionsChooser.getColumns());
        
        reactorCommands = new ReactorCommands().tap {
            onRandomize = { reactorGrid.randomize() }

            onSimulate = {
                reactorGrid.simulate(it.ticks)
                euGenerated.value = reactorGrid.euGenerated.intValue()
                heat.value = reactorGrid.heat.intValue()
            }
        }

        HorizontalLayout reactorLayout = new HorizontalLayout(reactorGrid, reactorCommands);
        
        HorizontalLayout reactorStats = new HorizontalLayout(euGenerated, heat);
        
        reactorSimulatorLayout.add(dimensionsChooser, reactorLayout, reactorStats);
        add(reactorSimulatorLayout);

    }

}
