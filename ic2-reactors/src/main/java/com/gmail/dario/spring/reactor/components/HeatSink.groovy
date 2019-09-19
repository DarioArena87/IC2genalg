package com.gmail.dario.spring.reactor.components

abstract class HeatSink extends Component {

    void addHeat(BigDecimal heat) {
        this.heat += heat
    }
}
