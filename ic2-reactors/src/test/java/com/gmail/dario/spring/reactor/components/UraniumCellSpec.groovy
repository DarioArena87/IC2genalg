package com.gmail.dario.spring.reactor.components

import com.gmail.dario.spring.reactor.Reactor
import com.gmail.dario.spring.reactor.components.uraniumcells.DualUraniumCell
import com.gmail.dario.spring.reactor.components.uraniumcells.SingleUraniumCell
import com.gmail.dario.spring.reactor.components.uraniumcells.UraniumCell

import spock.lang.Specification

class UraniumCellSpec extends Specification {

    Reactor reactor = new Reactor()

    def "Test Single Uranium Cell alone"(){
        given:
        UraniumCell uraniumCell = new SingleUraniumCell().tap { vessel = reactor }

        when:
        uraniumCell.tick()

        then:
        reactor.eu == 5
    }

    def "At the second cycle UraniumCell heat if not dissipated goes into the reactor"() {
        given:
        UraniumCell uraniumCell = new SingleUraniumCell().tap { vessel = reactor }

        when:
        uraniumCell.tick()
        uraniumCell.tick()

        then:
        reactor.heat == 4
    }

    def "Test two adiacent Single Uranium Cells"(){
        given:
        UraniumCell uraniumCell1 = new SingleUraniumCell().tap { vessel = reactor }
        UraniumCell uraniumCell2 = new SingleUraniumCell().tap { vessel = reactor }

        and:
        uraniumCell1.connect(uraniumCell2)
        uraniumCell2.connect(uraniumCell1)

        when:
        uraniumCell1.tick()
        uraniumCell2.tick()

        then:
        reactor.eu == 20
    }

    def "Adjacent Uranium Cells increase heat generation"(){
        given:
        UraniumCell uraniumCell1 = new SingleUraniumCell().tap { vessel = reactor }
        UraniumCell uraniumCell2 = new SingleUraniumCell().tap { vessel = reactor }

        and:
        uraniumCell1.connect(uraniumCell2)
        uraniumCell2.connect(uraniumCell1)

        when:
        uraniumCell1.tick()
        uraniumCell1.tick()
        uraniumCell2.tick()
        uraniumCell2.tick()

        then:
        reactor.heat == 24 //2 * 12 which is each cell generated heat
    }

    def "Test Dual Uranium Cell Alone" () {
        given:
        UraniumCell dualUraniumCell = new DualUraniumCell().tap { vessel = reactor }

        when:
        dualUraniumCell.tick()

        then:
        reactor.eu == 20
    }
}
