package com.gmail.dario.reactors.components.fuelcells

import com.gmail.dario.reactors.nulcearreactor.Reactor

import spock.lang.Specification

class UraniumCellsSpec extends Specification {
    
    def "Single uranium cell spec" () {
        given:
        Reactor reactor = Reactor.builder(6, 9).empty()
        
        and:
        UraniumCell uraniumCell = new SingleUraniumCell()
        
        when:
        reactor.install(uraniumCell, 3, 4)
        
        and:
        reactor.tick()
        
        then:
        reactor.eu == 5
        reactor.heat == 4
        uraniumCell.durabilityLeft == 199_999 / 200_000
    }
    
    def "Adjacent uranium cells spec" () {
        given:
        Reactor reactor = Reactor.builder(6, 9).empty()
        
        and:
        UraniumCell uraniumCell1 = new SingleUraniumCell()
        UraniumCell uraniumCell2 = new SingleUraniumCell()
        
        when:
        reactor.install(uraniumCell1, 3, 4)
        reactor.install(uraniumCell2, 3, 5)
        
        and:
        reactor.connectComponents()
        
        and:
        reactor.tick()
        
        then:
        reactor.eu == 20
        reactor.heat == 24
        uraniumCell1.durabilityLeft == 199_999 / 200_000
        uraniumCell2.durabilityLeft == 199_999 / 200_000
    }
}
