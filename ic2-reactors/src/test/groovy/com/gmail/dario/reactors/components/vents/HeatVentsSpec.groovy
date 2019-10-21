package com.gmail.dario.reactors.components.vents

import com.gmail.dario.reactors.components.ReactorComponent
import com.gmail.dario.reactors.nulcearreactor.Reactor

import spock.lang.Specification

class HeatVentsSpec extends Specification {
    def "Basic heat vent spec"(){
        given:
        Vent heatVent = new HeatVent(heat: 200)
        
        when:
        heatVent.tick()
        
        then:
        heatVent.heat == 194
    }
    
    def "Advanced heat vent spec"(){
        given:
        Vent heatVent = new AdvancedHeatVent(heat: 200)
        
        when:
        heatVent.tick()
        
        then:
        heatVent.heat == 188
    }
    
    def "Reactor heat vent spec"(){
        given:
        Reactor reactor = new Reactor(6, 9).tap { heat = 1000 }
        
        and:
        Vent heatVent = new ReactorHeatVent()
        
        when:
        reactor.install(heatVent, 0, 0)
        
        and:
        reactor.tick()
        
        then:
        reactor.heat == 995
        
        and:
        heatVent.heat == 0 
    }
    
    def "Overclocked heat vent spec"(){
        given:
        Reactor reactor = new Reactor(6, 9).tap { heat = 1000 }
        
        and:
        Vent heatVent = new OverclockedHeatVent()
        
        when:
        reactor.install(heatVent, 0, 0)
        
        and:
        reactor.tick()
        
        then:
        reactor.heat == 964
        
        and:
        heatVent.heat == 16
    }

    def "Component heat vent spec"() {
        given:
        Reactor reactor = new Reactor(6, 9).tap { heat = 1000 }

        and:
        ReactorComponent surroundingComponent1 = new HeatVent(heat: 10)
        ReactorComponent surroundingComponent2 = new HeatVent(heat: 15)
        ReactorComponent surroundingComponent3 = new HeatVent(heat: 12)
        ReactorComponent surroundingComponent4 = new HeatVent(heat: 12)
        ReactorComponent detachedComponent5 = new HeatVent(heat: 12)

        Vent heatVent = new ComponentHeatVent()

        when:
        reactor.install(heatVent, 1, 1)
        reactor.install(surroundingComponent1, 0, 1)
        reactor.install(surroundingComponent2, 1, 0)
        reactor.install(surroundingComponent3, 1, 2)
        reactor.install(surroundingComponent4, 2, 1)
        reactor.install(detachedComponent5, 3, 3)

        and:
        reactor.connectComponents()

        and:
        heatVent.tick()

        then:
        surroundingComponent1.heat == 6
        surroundingComponent2.heat == 11
        surroundingComponent3.heat == 8
        surroundingComponent4.heat == 8
        detachedComponent5.heat == 12

    }
}
