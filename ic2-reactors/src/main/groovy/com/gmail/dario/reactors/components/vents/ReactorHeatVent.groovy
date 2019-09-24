package com.gmail.dario.reactors.components.vents

class ReactorHeatVent extends Vent {

    @Override
    void tick() {
        dissipateHeatFromReactor(5.0)
        dissipateHeatToAir(5.0)
    }
}
