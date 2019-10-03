package com.gmail.dario.reactors.ui

import com.gmail.dario.reactors.ui.events.RandomizeEvent
import com.gmail.dario.reactors.ui.events.StartSimulationEvent
import com.vaadin.flow.component.ComponentEventListener
import com.vaadin.flow.component.Composite
import com.vaadin.flow.component.Text
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.component.textfield.NumberField
import com.vaadin.flow.shared.Registration
import groovy.transform.CompileStatic

@CompileStatic
class ReactorCommands extends Composite<VerticalLayout> {

    final Button randomize = new Button("Randomize", { fireEvent(new RandomizeEvent(this)) })

    final NumberField ticks = new NumberField(hasControls: true, step: 1, value: 6000)

    final Button simulate = new Button("Simulate", { fireEvent(new StartSimulationEvent(this, ticks.value.intValue())) })

    final Button evolve = new Button("Evolve")

    ReactorCommands() {
        content.add(
                randomize,
                new HorizontalLayout(simulate, ticks, new Text("Ticks")),
                evolve
        )
    }

    Registration setOnRandomize(final ComponentEventListener<RandomizeEvent> e) {
        return addListener(RandomizeEvent.class, e)
    }

    Registration setOnSimulate(final ComponentEventListener<StartSimulationEvent> e) {
        return addListener(StartSimulationEvent.class, e)
    }

}
