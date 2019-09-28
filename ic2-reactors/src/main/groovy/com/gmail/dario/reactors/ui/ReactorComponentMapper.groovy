package com.gmail.dario.reactors.ui

import com.gmail.dario.reactors.components.EmptyCell
import com.gmail.dario.reactors.components.ReactorComponent
import com.gmail.dario.reactors.components.coolantcells.CoolantCell10K
import com.gmail.dario.reactors.components.coolantcells.CoolantCell30K
import com.gmail.dario.reactors.components.coolantcells.CoolantCell60K
import com.gmail.dario.reactors.components.coolantcells.LZHCondensator
import com.gmail.dario.reactors.components.coolantcells.RSHCondensator
import com.gmail.dario.reactors.components.fuelcells.DualUraniumCell
import com.gmail.dario.reactors.components.fuelcells.QuadUraniumCell
import com.gmail.dario.reactors.components.fuelcells.SingleUraniumCell
import com.gmail.dario.reactors.components.heatexchangers.AdvancedHeatExchanger
import com.gmail.dario.reactors.components.heatexchangers.ComponentHeatExchanger
import com.gmail.dario.reactors.components.heatexchangers.HeatExchanger
import com.gmail.dario.reactors.components.heatexchangers.ReactorHeatExchanger
import com.gmail.dario.reactors.components.reflectors.NeutronReflector
import com.gmail.dario.reactors.components.reflectors.ThickNeutronReflector
import com.gmail.dario.reactors.components.vents.AdvancedHeatVent
import com.gmail.dario.reactors.components.vents.ComponentHeatVent
import com.gmail.dario.reactors.components.vents.HeatVent
import com.gmail.dario.reactors.components.vents.OverclockedHeatVent
import com.gmail.dario.reactors.components.vents.ReactorHeatVent
import groovy.transform.CompileStatic

@CompileStatic
enum ReactorComponentMapper {

    EMPTY_CELL(0, EmptyCell, {new EmptyCell()}, "", "Empty cell"),

    SINGLE_URANIUM_CELL(1, SingleUraniumCell, {new SingleUraniumCell()}, "Grid_Uranium_Cell.png", "Uranium Cell"),
    DUAL_URANIUM_CELL(2, DualUraniumCell, {new DualUraniumCell()}, "Grid_Dual_Uranium_Cell.png", "Dual Uranium Cell"),
    QUAD_URANIUMCELL(3, QuadUraniumCell, { new QuadUraniumCell()}, "Grid_Quad_Fuel_Rod_(Uranium).png", "Quad Fuel Rod (Uranium)"),

    HEAT_VENT(4, HeatVent, { new HeatVent()}, "Grid_Heat_Vent.png", "Heat Vent"),
    REACTOR_HEAT_VENT(5, ReactorHeatVent, {new ReactorHeatVent()}, "Grid_Reactor_Heat_Vent.png", "Reactor Heat Vent"),
    ADVANCED_HEAT_VENT(6, AdvancedHeatVent, {new AdvancedHeatVent()}, "Grid_Advanced_Heat_Vent.png", "Advanced Heat Vent"),
    COMPONENT_HEAT_VENT(7, ComponentHeatVent, {new ComponentHeatVent()}, "Grid_Component_Heat_Vent.png", "Component Heat Vent"),
    OVERCLOCKED_HEAT_VENT(8, OverclockedHeatVent, {new OverclockedHeatVent()}, "Grid_Overclocked_Heat_Vent.png", "Overclocked Heat Vent"),

    HEAT_EXCHANGER(9, HeatExchanger, { new HeatExchanger()}, "Grid_Heat_Exchanger.png", "Heat Exchanger"),
    ADVANCED_HEAT_EXCHANGER(10, AdvancedHeatExchanger, { new AdvancedHeatExchanger()}, "Grid_Advanced_Heat_Exchanger.png", "Advanced Heat Exchanger"),
    REACTOR_HEAT_EXCHANGER(11, ReactorHeatExchanger, { new ReactorHeatExchanger()}, "Grid_Core_Heat_Exchanger.png", "Reactor Heat Exchanger"),
    COMPONENT_HEAT_EXCHANGER(12, ComponentHeatExchanger, { new ComponentHeatExchanger()}, "Grid_Component_Heat_Exchanger.png", "Component Heat Exchanger"),

    COOLANT_CELL_10K(13, CoolantCell10K, { new CoolantCell10K()}, "Grid_10k_Coolant_Cell.png", "10k Coolant Cell"),
    COOLANT_CELL_30K(14, CoolantCell30K, {new CoolantCell30K()}, "Grid_30k_Coolant_Cell.png", "30k Coolant Cell"),
    COOLANT_CELL_60K(15, CoolantCell60K, { new CoolantCell60K()}, "Grid_60k_Coolant_Cell.png", "60k Coolant Cell"),
    RSH_CONDENSATOR(16, RSHCondensator, { new RSHCondensator()}, "Grid_RSH-Condensator.png", "RSH-Condensator"),
    LZH_CONDENSATOR(17, LZHCondensator, { new LZHCondensator()}, "Grid_LZH-Condensator.png", "LZH-Condensator"),

    NEUTRON_REFLECTOR(17, NeutronReflector, { new NeutronReflector()}, "Grid_Thick_Neutron_Reflector.png", "Thick Neutron Reflector"),
    THICK_NEUTRON_REFLECTOR(17, ThickNeutronReflector, { new ThickNeutronReflector()}, "Grid_Neutron_Reflector.png", "Neutron Reflector");

    final int id
    private final Class clazz
    private final Closure<ReactorComponent> createClosure
    final String image
    final String description

    private ReactorComponentMapper(int id, Class clazz, Closure<ReactorComponent> createClosure, String image, String description) {
        this.id = id
        this.clazz = clazz
        this.createClosure = createClosure
        this.image = image
        this.description = description
    }

    static int getComponentId(ReactorComponent reactorComponent) {
        values().find {reactorComponent in it.clazz}?.id ?: 0
    }
    
    static ReactorComponentMapper getAt(int componentId) {
        values().find { it.id == componentId } ?: EMPTY_CELL
    }

    String getComponentImage(int componentId) {
        values().find {it.id == componentId}?.image ?: EMPTY_CELL.image
    }

    ReactorComponent create(){
        createClosure.call()
    }

}
