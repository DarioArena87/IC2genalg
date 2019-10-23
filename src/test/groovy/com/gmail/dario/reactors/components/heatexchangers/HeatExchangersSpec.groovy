package com.gmail.dario.reactors.components.heatexchangers

import com.gmail.dario.reactors.components.ReactorComponent
import com.gmail.dario.reactors.nulcearreactor.Reactor
import spock.lang.PendingFeature
import spock.lang.Specification

class HeatExchangersSpec extends Specification {

    def "Basic Heat Exchanger spec"() {
        given:
        Reactor reactor = Reactor.builder(6, 9).empty().tap { heat = 1500 }

        and:
        ReactorComponent heatExchanger = new HeatExchanger()

        when:
        reactor.install(heatExchanger, 4, 4)

        and:
        reactor.tick()

        then:
        reactor.heat == 1496
        heatExchanger.heat == 4
    }

    def "Mediating ability of Heat Exchanger spec"() {
        given:
        Reactor reactor = Reactor.builder(6, 9).empty().tap { heat = 1002 }

        and:
        ReactorComponent heatExchanger = new HeatExchanger(heat: 248)

        when:
        reactor.install(heatExchanger, 4, 4)

        and:
        300.times { reactor.tick() }

        then:
        reactor.heat == 1002
        heatExchanger.heat == 248
    }
}
