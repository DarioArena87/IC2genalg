package com.gmail.dario.reactors.ui.reactorcommands

import com.gmail.dario.reactors.ui.reactorcommands.events.RandomizeEvent
import com.gmail.dario.reactors.ui.reactorcommands.events.StartEvolutionEvent
import com.gmail.dario.reactors.ui.reactorcommands.events.StartSimulationEvent
import com.gmail.dario.reactors.ui.reactorcommands.events.StopSimulationEvent
import com.vaadin.flow.component.ComponentEventListener
import com.vaadin.flow.component.Composite
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.icon.Icon
import com.vaadin.flow.component.icon.VaadinIcon
import com.vaadin.flow.component.orderedlayout.FlexComponent
import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.component.textfield.NumberField
import com.vaadin.flow.shared.Registration
import groovy.transform.CompileStatic

@CompileStatic
class ReactorCommands extends Composite<VerticalLayout> {

    final Button randomize = new Button("Randomize", { fireEvent(new RandomizeEvent(this)) })

    final NumberField ticks = new NumberField(hasControls: true, step: 1, value: 10_000, min: 0, max: 500_000, label: "Ticks")

    final Button simulate = new Button(new Icon(VaadinIcon.PLAY), { fireEvent(new StartSimulationEvent(this, ticks.value.intValue())) })

    final Button stopSimulation = new Button(new Icon(VaadinIcon.STOP), { fireEvent(new StopSimulationEvent(this)) })

    final Button evolve = new Button("Evolve", { fireEvent(new StartEvolutionEvent(this, ticks.value.intValue())) })

    ReactorCommands() {
        def simulationControls = new HorizontalLayout().tap {
            addAndExpand simulate, ticks, stopSimulation
            alignItems = FlexComponent.Alignment.BASELINE
        }

        content.add(
            randomize,
            simulationControls,
            evolve
        )
    }

    Registration setOnRandomize(ComponentEventListener<RandomizeEvent> e) {
        return addListener(RandomizeEvent, e)
    }

    Registration setOnSimulate(ComponentEventListener<StartSimulationEvent> e) {
        return addListener(StartSimulationEvent, e)
    }

    Registration setOnStopSimulation(ComponentEventListener<StopSimulationEvent> e) {
        return addListener(StopSimulationEvent, e)
    }

    Registration setOnStartEvolution(ComponentEventListener<StartEvolutionEvent> e) {
        return addListener(StartEvolutionEvent, e)
    }

}
