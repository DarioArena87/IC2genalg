package com.gmail.dario.reactors.components.vents

class AdvancedHeatVent extends Vent {

    @Override
    void tick() {
        dissipateHeatToAir(12)
    }
}
