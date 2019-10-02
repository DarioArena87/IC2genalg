package com.gmail.dario.reactors.ui

import com.gmail.dario.reactors.ui.events.DimensionChangeEvent
import com.gmail.dario.reactors.ui.ReactorCommands
import com.vaadin.flow.component.html.Div
import com.vaadin.flow.component.html.H2
import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.component.textfield.NumberField
import com.vaadin.flow.data.binder.Binder
import com.gmail.dario.reactors.ui.events.StartSimulationEvent

class ReactorSimulation extends HorizontalLayout {

    DimensionsChooser dimensionsChooser = new DimensionsChooser().tap {
        onDimensionChange = { DimensionChangeEvent e ->
            reactorGrid.setDimensions(e.newRows, e.newColumns)
            euGenerated.value = reactorGrid.euGenerated.intValue()
            heat.value = reactorGrid.heat.intValue()
        }
    }

    ReactorGrid reactorGrid = new ReactorGrid(dimensionsChooser.rows, dimensionsChooser.columns);

    ReactorCommands reactorCommands = new ReactorCommands().tap {
        onRandomize = {
            reactorGrid.randomize()
        }

        onSimulate = { StartSimulationEvent e ->
            reactorGrid.simulate(e.ticks)
            euGenerated.value = reactorGrid.euGenerated.intValue()
            heat.value = reactorGrid.heat.intValue()
        }
    }

    NumberField euGenerated = new NumberField(label: "EU", value: 0)

    NumberField heat = new NumberField(label: "Heat", value: 0)

    ReactorSimulation() {

        add(
                new Div(new H2("Reactor components"), new ReactorComponentsAccordion())
        )

        add(
                new VerticalLayout(
                        dimensionsChooser,
                        new HorizontalLayout(reactorGrid, reactorCommands),
                        new HorizontalLayout(euGenerated, heat)
                )
        )

    }

}
