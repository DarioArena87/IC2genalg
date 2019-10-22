package com.gmail.dario.reactors.components.heatexchangers

class HeatExchanger extends HeatMover {

    final int maxHeat = 2_500

    @Override
    void tick() {
        transferHeatWithConnectedComponents(12)
        transferHeatWithReactor(4)
    }
    
}
