package com.gmail.dario.reactors.components.vents

class OverclockedHeatVent extends Vent {

    @Override
    void tick() {
        dissipateHeatFromReactor(36)
        dissipateHeatToAir(20)
    }
}
