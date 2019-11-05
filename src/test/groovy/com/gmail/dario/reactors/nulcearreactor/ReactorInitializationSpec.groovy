package com.gmail.dario.reactors.nulcearreactor

import com.gmail.dario.reactors.components.fuelcells.DualUraniumCell
import com.gmail.dario.reactors.components.fuelcells.SingleUraniumCell
import com.gmail.dario.reactors.components.fuelcells.UraniumCell
import com.gmail.dario.reactors.ui.ReactorComponentMapper
import spock.lang.Specification

class ReactorInitializationSpec extends Specification {
    
    
    def "Reactor can be initialized empty"(){
        Reactor reactor = Reactor.builder().empty()
        
        expect:
        reactor.rows == 0
        reactor.columns == 0
        reactor.components == []
    }
    
    def "I can set rows and columns of reactor"(){
        given:
        Reactor reactor = Reactor.builder(6, 9).empty()
        
        expect:
        reactor.rows == 6
        reactor.columns == 9
        reactor.components.size() == 6
        reactor.components.every { it.size() == 9 }
        
    }
    
    def "adjacent components inside reactor are connected"(){
        given:
        Reactor reactor = Reactor.builder(6, 9).empty()
        
        and:
        UraniumCell uraniumCell1 = new SingleUraniumCell()
        UraniumCell uraniumCell2 = new DualUraniumCell()
        UraniumCell uraniumCell3 = new SingleUraniumCell()
        
        when:
        reactor.install(uraniumCell1, 3, 4)
        reactor.install(uraniumCell2, 3, 5)
        reactor.install(uraniumCell3, 5, 6)
        
        and:
        reactor.connectComponents()
        
        then:
        uraniumCell1.connectedComponents.size() == 1
        uraniumCell2 in uraniumCell1.connectedComponents
        
        uraniumCell2.connectedComponents.size() == 1
        uraniumCell1 in uraniumCell2.connectedComponents
        
        uraniumCell3.connectedComponents.empty
    }

    def "Reactor initialization with lazy list"(){
        given:
        def componentIds = [].withDefault { ReactorComponentMapper.EMPTY_CELL.id }
        componentIds[22] = ReactorComponentMapper.QUAD_URANIUMCELL.id

        when:
        Reactor reactor = Reactor.builder(6, 9).fromComponentIds(componentIds)

        then:
        reactor.rows == 6
        reactor.columns == 9
    }
}
