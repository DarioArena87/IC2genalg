package com.gmail.dario.reactors.components.heatexchangers

class ReactorHeatExchanger extends HeatMover {

    final int maxHeat = 5_000

    @Override
    void tick() {
        transferHeatWithReactor(72)
    }
}
