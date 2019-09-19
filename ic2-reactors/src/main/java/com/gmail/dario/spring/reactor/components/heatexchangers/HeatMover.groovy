package com.gmail.dario.spring.reactor.components.heatexchangers

import com.gmail.dario.spring.reactor.components.Component
import com.gmail.dario.spring.reactor.components.HeatSink

abstract class HeatMover extends Component {

    void exchangeHeatWithConnectedComponents(BigDecimal maxTransferredHeat) {
        Collection<BigDecimal> distancesToDestrOfConnectedComponent = connectedComponents.collect { 1 - it.heat/it.maxHeat}

        BigDecimal myDistanceToDestr = 1 - heat/maxHeat

        BigDecimal avgDistancesToDestr = ((distancesToDestrOfConnectedComponent.sum() as BigDecimal) + myDistanceToDestr) /
                                         (distancesToDestrOfConnectedComponent.size() + 1)

        for (Component component in connectedComponents) {
            BigDecimal heatToTransfer = component.maxHeat * (1 - avgDistancesToDestr) - component.heat
            if (heatToTransfer < 0) {
                heat += component.drawnHeat(Math.min(maxTransferredHeat, heatToTransfer.abs()))
            }
            else if (component in HeatSink && heatToTransfer > 0) {
                BigDecimal sentHeat = Math.min(heat, Math.min(maxTransferredHeat, heatToTransfer))
                component.acceptHeat(sentHeat)
                heat -= sentHeat
            }
        }
    }

    void exchangeHeatWithVessel(BigDecimal maxTransferredHeat) {

        BigDecimal vesselDistanceToDestr = 1 - vessel.heat/vessel.maxHeat

        BigDecimal myDistanceToDestr = 1 - heat/maxHeat

        BigDecimal avgDistToDescr = (vesselDistanceToDestr + myDistanceToDestr) / 2

        BigDecimal heatToTransfer = vessel.maxHeat * (1 - avgDistToDescr) - vessel.heat

        if (heatToTransfer < 0) {
            heat += vessel.drawnHeat(Math.min(maxTransferredHeat, heatToTransfer.abs()))
        }
        else if (heatToTransfer > 0) {
            BigDecimal sentHeat = Math.min(heat, Math.min(maxTransferredHeat, heatToTransfer))
            vessel.acceptHeat(heatToTransfer.abs())
            heat -= sentHeat
        }
    }
}
