package com.gmail.dario.reactors.components

import static com.gmail.dario.reactors.utils.Bounder.bound

abstract class HeatingObject extends ReactorComponent {
    
    BigDecimal heat = 0
    
    BigDecimal removeHeat(BigDecimal heatToGet) {
        BigDecimal drawnHeat = bound heatToGet toAtMost heat
        heat -= drawnHeat
        return drawnHeat
    }
    
    void putHeat(BigDecimal heatToPut) {
        heat += heatToPut
    }
    
    BigDecimal getHeatPercentage() {
        heat / maxHeat
    }

    @Override
    BigDecimal getDurabilityLeft() {
        1 - heatPercentage
    }

    abstract BigDecimal getMaxHeat()
}
