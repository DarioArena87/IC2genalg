package com.gmail.dario.spring.reactor

import com.gmail.dario.spring.reactor.components.Component
import com.gmail.dario.spring.reactor.components.cooling.CoolantCell10K
import com.gmail.dario.spring.reactor.components.cooling.CoolantCell30K
import com.gmail.dario.spring.reactor.components.cooling.CoolantCell60K
import com.gmail.dario.spring.reactor.components.cooling.LZHCondensator
import com.gmail.dario.spring.reactor.components.cooling.RSHCondensator
import com.gmail.dario.spring.reactor.components.heatexchangers.AdvancedHeatExchanger
import com.gmail.dario.spring.reactor.components.heatexchangers.ComponentHeatExchanger
import com.gmail.dario.spring.reactor.components.heatexchangers.HeatExchanger
import com.gmail.dario.spring.reactor.components.heatexchangers.ReactorHeatExchanger
import com.gmail.dario.spring.reactor.components.heatvents.AdvancedHeatVent
import com.gmail.dario.spring.reactor.components.heatvents.ComponentHeatVent
import com.gmail.dario.spring.reactor.components.heatvents.HeatVent
import com.gmail.dario.spring.reactor.components.heatvents.OverclockedHeatVent
import com.gmail.dario.spring.reactor.components.heatvents.ReactorHeatVent
import com.gmail.dario.spring.reactor.components.reflectors.NeutronReflector
import com.gmail.dario.spring.reactor.components.reflectors.ThickNeutronReflector
import com.gmail.dario.spring.reactor.components.uraniumcells.DualUraniumCell
import com.gmail.dario.spring.reactor.components.uraniumcells.QuadUraniumCell
import com.gmail.dario.spring.reactor.components.uraniumcells.SingleUraniumCell

class MappedComponent {
    Closure component
    String image
}

class ComponentMapper {

    Map<Integer, MappedComponent> components = [
        0:  new MappedComponent(component: null,                           image: null),
        1:  new MappedComponent(component: {new HeatVent()},               image: "Grid_Heat_Vent.png"),
        2:  new MappedComponent(component: {new ReactorHeatVent()},        image: "Grid_Reactor_Heat_Vent.png"),
        3:  new MappedComponent(component: {new AdvancedHeatVent()},       image: "Grid_Advanced_Heat_Vent.png"),
        4:  new MappedComponent(component: {new ComponentHeatVent()},      image: "Grid_Component_Heat_Vent.png"),
        5:  new MappedComponent(component: {new OverclockedHeatVent()},    image: "Grid_Overclocked_Heat_Vent.png"),
        6:  new MappedComponent(component: {new HeatExchanger()},          image: "Grid_Heat_Exchanger.png"),
        7:  new MappedComponent(component: {new AdvancedHeatExchanger()},  image: "Grid_Advanced_Heat_Exchanger.png"),
        8:  new MappedComponent(component: {new ReactorHeatExchanger()},   image: "Grid_Reactor_Heat_Vent.png"),
        9:  new MappedComponent(component: {new ComponentHeatExchanger()}, image: "Grid_Component_Heat_Exchanger.png"),
        10: new MappedComponent(component: {new CoolantCell10K()},         image: "Grid_10k_Coolant_Cell.png"),
        11: new MappedComponent(component: {new CoolantCell30K()},         image: "Grid_30k_Coolant_Cell.png"),
        12: new MappedComponent(component: {new CoolantCell60K()},         image: "Grid_60k_Coolant_Cell.png"),
        13: new MappedComponent(component: {new RSHCondensator()},         image: "Grid_RSH-Condensator.png"),
        14: new MappedComponent(component: {new LZHCondensator()},         image: "Grid_LZH-Condensator.png"),
        15: new MappedComponent(component: {new SingleUraniumCell()},      image: "Grid_Uranium_Cell.png"),
        16: new MappedComponent(component: {new DualUraniumCell()},        image: "Grid_Dual_Uranium_Cell.png"),
        17: new MappedComponent(component: {new QuadUraniumCell()},        image: "Grid_Quad_Fuel_Rod_(Uranium).png"),
        18: new MappedComponent(component: {new NeutronReflector()},       image: "Grid_Neutron_Reflector.png"),
        19: new MappedComponent(component: {new ThickNeutronReflector()},  image: "Grid_Thick_Neutron_Reflector.png")
    ]

    Component getComponent(int componentId) {
        components[componentId]?.component?.call()
    }

    String getComponentImage(int componentId) {
        components[componentId]?.image
    }
}
