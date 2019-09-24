package com.gmail.dario.reactors.components.coolantcells

import com.gmail.dario.reactors.components.HeatingObject
import com.gmail.dario.reactors.components.ReactorComponent

class CoolantCell60K extends HeatingObject {

    final BigDecimal maxHeat = 60_000
    
    @Override
    void tick() {/* do nothing*/}
}
