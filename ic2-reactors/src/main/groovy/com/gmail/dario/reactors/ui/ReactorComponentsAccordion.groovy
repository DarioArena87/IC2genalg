package com.gmail.dario.reactors.ui

import com.vaadin.flow.component.Component
import com.vaadin.flow.component.accordion.Accordion
import com.vaadin.flow.component.accordion.AccordionPanel
import groovy.transform.CompileStatic

import static com.gmail.dario.reactors.ui.ReactorComponentMapper.*

@CompileStatic
class ReactorComponentsAccordion extends Accordion {

    private static final long serialVersionUID = 1970933654950554248L

    ReactorComponentsAccordion() {
        addPanel("Uranium Cells", SINGLE_URANIUM_CELL, DUAL_URANIUM_CELL, QUAD_URANIUMCELL)
        addPanel("Vents", HEAT_VENT, REACTOR_HEAT_VENT, ADVANCED_HEAT_VENT, COMPONENT_HEAT_VENT, OVERCLOCKED_HEAT_VENT)
        addPanel("Heat Exchangers", HEAT_EXCHANGER, ADVANCED_HEAT_EXCHANGER, REACTOR_HEAT_EXCHANGER, COMPONENT_HEAT_EXCHANGER)
        addPanel("Cooling Cells and Condensators", COOLANT_CELL_10K, COOLANT_CELL_30K, COOLANT_CELL_60K)
        addPanel("Reflectors", NEUTRON_REFLECTOR, THICK_NEUTRON_REFLECTOR)
        addPanel("Reactor Platings", REACTOR_PLATING, CONTAINMENT_REACTOR_PLATING, HEAT_CAPACITY_REACTOR_PLATING)
    }

    private AccordionPanel addPanel(String title, ReactorComponentMapper... components) {
        add new AccordionPanel(summaryText: title).tap {
            addContent(components.collect { new ReactorComponentLabel(it) } as Component[])
        }
    }
}
