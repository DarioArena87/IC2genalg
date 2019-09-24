package com.gmail.dario.reactors.components.coolantcells

import com.gmail.dario.reactors.components.HeatingObject
import com.gmail.dario.reactors.components.ReactorComponent

class LZHCondensator extends HeatingObject {

    final BigDecimal maxHeat = 100_000
    
    @Override
    void tick() {/* do nothing*/}
}
