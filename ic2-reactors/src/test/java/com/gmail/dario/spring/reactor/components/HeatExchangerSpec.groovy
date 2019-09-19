package com.gmail.dario.spring.reactor.components
import com.gmail.dario.spring.reactor.Reactor
import com.gmail.dario.spring.reactor.components.heatexchangers.HeatExchanger
import com.gmail.dario.spring.reactor.components.heatvents.HeatVent

import spock.lang.Specification

class HeatExchangerSpec extends Specification {

    Reactor reactor = new Reactor()
    HeatExchanger heatExchanger = new HeatExchanger().tap { vessel = reactor }

    def "When all components are at same distance to distruction no heat is moved" () {
        given:
        Component c1 = new HeatVent().tap { heat = 100 }
        Component c2 = new HeatVent().tap { heat = 100 }

        heatExchanger.with {
            heat = 250
            connect(c1)
            connect(c2)
        }

        when:
        heatExchanger.tick()

        then:
        c1.heat == 100
        c2.heat == 100
    }

    def "When connected components have different heat values distance to destrucion equalizes" () {
        given:
        Component c1 = new HeatVent().tap { heat = 105 }
        Component c2 = new HeatVent().tap { heat = 95 }

        heatExchanger.with {
            heat = 250
            connect(c1)
            connect(c2)
        }

        when:
        heatExchanger.tick()

        then:
        c1.heat == 100
        c2.heat == 100
    }

    def "Heat exchanger can even difference to reactor destruction" () {
        given:
        Component c1 = new HeatVent().tap { heat = 25 }

        heatExchanger.with {
            heat = 200
            connect(c1)
        }

        reactor.heat = 400

        when:
        heatExchanger.tick()

        then:
        c1.heat == 37
        heatExchanger.heat == 192
        reactor.heat == 396

    }
}
