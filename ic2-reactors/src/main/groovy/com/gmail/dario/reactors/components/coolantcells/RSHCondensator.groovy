package com.gmail.dario.reactors.components.coolantcells

import com.gmail.dario.reactors.components.HeatingObject
import com.gmail.dario.reactors.components.ReactorComponent

class RSHCondensator extends HeatingObject {

    final BigDecimal maxHeat = 20_000
    
    @Override
    void tick() {/* do nothing*/}
}
