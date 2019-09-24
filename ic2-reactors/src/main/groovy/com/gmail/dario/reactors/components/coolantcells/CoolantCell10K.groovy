package com.gmail.dario.reactors.components.coolantcells

import com.gmail.dario.reactors.components.HeatingObject
import com.gmail.dario.reactors.components.ReactorComponent

class CoolantCell10K extends HeatingObject {

    final BigDecimal maxHeat = 10_000
    
    @Override
    void tick() {/* do nothing*/}
}
