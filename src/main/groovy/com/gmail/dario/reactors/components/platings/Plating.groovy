package com.gmail.dario.reactors.components.platings

import com.gmail.dario.reactors.components.ReactorComponent

abstract class Plating extends ReactorComponent {
    abstract int getHeatResistance()

    @Override
    double getDurabilityLeft() { 1.0 /* never destroys */}

    @Override
    void tick() {/*Does nothing */ }
}
