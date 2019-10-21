package com.gmail.dario.reactors.components.vents

class HeatVent extends Vent {

    @Override
    void tick() {
        dissipateHeatToAir(6)
    }

}
