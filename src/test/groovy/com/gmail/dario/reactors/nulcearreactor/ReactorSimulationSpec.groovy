package com.gmail.dario.reactors.nulcearreactor


import com.gmail.dario.reactors.components.coolantcells.CoolantCell10K
import com.gmail.dario.reactors.components.fuelcells.QuadUraniumCell
import com.gmail.dario.reactors.components.heatexchangers.ComponentHeatExchanger
import com.gmail.dario.reactors.components.heatexchangers.HeatExchanger
import com.gmail.dario.reactors.components.heatexchangers.ReactorHeatExchanger
import com.gmail.dario.reactors.components.vents.OverclockedHeatVent
import spock.lang.Specification

class ReactorSimulationSpec extends Specification {

    def "simulation of heat vents"() {
        given:
        Reactor reactor = Reactor.builder(6, 9).empty()
        reactor.install(new QuadUraniumCell(), 0, 0)
        reactor.install(new QuadUraniumCell(), 0, 1)
        reactor.install(new QuadUraniumCell(), 0, 2)
        reactor.install(new HeatExchanger(), 1, 1)
        def vent = new OverclockedHeatVent()
        reactor.install(vent, 1, 2)

        reactor.connectComponents()

        when:
        2.times { reactor.tick() }

        then:
        reactor.heat == 520
        vent.durabilityLeft < 1
    }

    def "simulation of coolant cells"() {
        given:
        Reactor reactor = Reactor.builder(6, 9).empty()
        reactor.install(new QuadUraniumCell(), 0, 0)
        reactor.install(new QuadUraniumCell(), 0, 1)
        reactor.install(new QuadUraniumCell(), 0, 2)
        reactor.install(new ReactorHeatExchanger(heat: 4_800), 0, 1)
        reactor.install(new ComponentHeatExchanger(heat: 4_800), 1, 1)
        def coolantCell = new CoolantCell10K(heat: 5000)
        reactor.install(coolantCell, 1, 2)

        reactor.connectComponents()

        when:
        reactor.tick()

        then:
        reactor.heat > 0
        coolantCell.durabilityLeft < 0.5
    }
}
