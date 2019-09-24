package com.gmail.dario.reactors.components.heatexchangers

class HeatExchanger extends HeatMover {

    final BigDecimal maxHeat = 2_500

    @Override
    void tick() {
        transferHeatWithConnectedComponents(12.0)
        transferHeatWithReactor(4.0)
    }
    
}
