package com.gmail.dario.reactors.ui

import com.gmail.dario.reactors.components.ReactorComponent
import com.gmail.dario.reactors.components.fuelcells.DualUraniumCell

import spock.lang.Specification

class ReactorComponentMapperSpec extends Specification {
    def "creating reactor components via mapper"(){
        given:
        ReactorComponent component = ReactorComponentMapper.DUAL_URANIUM_CELL.create()
        
        expect:
        component != null
        component in DualUraniumCell
    }
}
