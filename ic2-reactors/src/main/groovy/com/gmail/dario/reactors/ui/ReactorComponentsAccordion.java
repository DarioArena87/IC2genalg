package com.gmail.dario.reactors.ui;

import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.accordion.AccordionPanel;

public class ReactorComponentsAccordion extends Accordion {

    private static final long serialVersionUID = 1970933654950554248L;

    public ReactorComponentsAccordion() {
        final AccordionPanel uraniumCells = new AccordionPanel();
        uraniumCells.setSummaryText("Uranium Cells");
        uraniumCells.addContent(new ReactorComponentLabel("Grid_Uranium_Cell.png", "Uranium Cell"),
                                new ReactorComponentLabel("Grid_Dual_Uranium_Cell.png", "Dual Uranium Cell"),
                                new ReactorComponentLabel("Grid_Quad_Fuel_Rod_(Uranium).png", "Quad Fuel Rod (Uranium)"));
        add(uraniumCells);

        final AccordionPanel vents = new AccordionPanel();
        vents.setSummaryText("Vents");
        vents.addContent(new ReactorComponentLabel("Grid_Heat_Vent.png", "Heat Vent"),
                         new ReactorComponentLabel("Grid_Reactor_Heat_Vent.png", "Reactor Heat Vent"),
                         new ReactorComponentLabel("Grid_Advanced_Heat_Vent.png", "Advanced Heat Vent"),
                         new ReactorComponentLabel("Grid_Component_Heat_Vent.png", "Component Heat Vent"),
                         new ReactorComponentLabel("Grid_Overclocked_Heat_Vent.png", "Overclocked Heat Vent"));

        add(vents);

        final AccordionPanel heatExchangers = new AccordionPanel();
        heatExchangers.setSummaryText("Heat Exchangers");
        heatExchangers.addContent(new ReactorComponentLabel("Grid_Heat_Exchanger.png", "Heat Exchanger"),
                                  new ReactorComponentLabel("Grid_Advanced_Heat_Exchanger.png", "Advanced Heat Exchanger"),
                                  new ReactorComponentLabel("Grid_Core_Heat_Exchanger.png", "Reactor Heat Exchanger"),
                                  new ReactorComponentLabel("Grid_Component_Heat_Exchanger.png", "Component Heat Exchanger"));

        add(heatExchangers);

        final AccordionPanel cooling = new AccordionPanel();
        cooling.setSummaryText("Cooling Cells and Condensators");
        cooling.addContent(new ReactorComponentLabel("Grid_10k_Coolant_Cell.png", "10k Coolant Cell"),
                           new ReactorComponentLabel("Grid_30k_Coolant_Cell.png", "30k Coolant Cell"),
                           new ReactorComponentLabel("Grid_60k_Coolant_Cell.png", "60k Coolant Cell"),
                           new ReactorComponentLabel("Grid_RSH-Condensator.png", "RSH-Condensator"),
                           new ReactorComponentLabel("Grid_LZH-Condensator.png", "LZH-Condensator"));

        add(cooling);

        final AccordionPanel reflectors = new AccordionPanel();
        reflectors.setSummaryText("Reflectors");
        reflectors.addContent(new ReactorComponentLabel("Grid_Thick_Neutron_Reflector.png", "Thick Neutron Reflector"),
                              new ReactorComponentLabel("Grid_Neutron_Reflector.png", "Neutron Reflector"));

        add(reflectors);

    }
}
