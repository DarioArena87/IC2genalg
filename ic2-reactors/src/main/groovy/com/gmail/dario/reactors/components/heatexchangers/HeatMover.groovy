package com.gmail.dario.reactors.components.heatexchangers

import com.gmail.dario.reactors.components.HeatingObject
import com.gmail.dario.reactors.utils.FastMath
import groovy.transform.CompileStatic

import static com.gmail.dario.reactors.utils.Bounder.bound
import static com.gmail.dario.reactors.utils.FastMath.average

@CompileStatic
abstract class HeatMover extends HeatingObject {

    void transferHeatWithConnectedComponents(BigDecimal maxTransferredHeat) {
        List<HeatingObject> heatingConnectedComponents = connectedComponents.findAll { it in HeatingObject } as List<HeatingObject>
        List<BigDecimal> durabilities = heatingConnectedComponents*.durabilityLeft + durabilityLeft

        BigDecimal averageDurabilityLeft = average(durabilities)

        heatingConnectedComponents.each {
            BigDecimal heatToTransfer = it.maxHeat * (1 - averageDurabilityLeft) - it.heat
            if (heatToTransfer < 0) {
                heat += it.removeHeat(bound(heatToTransfer.abs()).toAtMost(maxTransferredHeat))
            }
            else {
                BigDecimal heatTransferred = bound(heatToTransfer).toAtMost(maxTransferredHeat)
                it.putHeat(heatTransferred)
                heat -= heatTransferred
            }
        }
    }

    void transferHeatWithReactor(BigDecimal maxTransferredHeat) {
        BigDecimal averageDurabilityLeft = average([vessel.durabilityLeft, durabilityLeft])
        BigDecimal heatToTransfer = vessel.maxHeat * (1 - averageDurabilityLeft) - vessel.heat

        if (heatToTransfer < 0) {
            heat += vessel.removeHeat(bound(heatToTransfer.abs()).toAtMost(maxTransferredHeat))
        }
        else {
            BigDecimal heatTransferred = bound(heatToTransfer).toAtMost(maxTransferredHeat)
            vessel.putHeat(heatTransferred)
            heat -= heatTransferred
        }
    }
}
