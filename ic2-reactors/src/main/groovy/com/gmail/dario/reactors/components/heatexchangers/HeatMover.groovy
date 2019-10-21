package com.gmail.dario.reactors.components.heatexchangers

import com.gmail.dario.reactors.components.HeatingObject
import groovy.transform.CompileStatic

import static com.gmail.dario.reactors.utils.Bounder.bound
import static com.gmail.dario.reactors.utils.FastMath.average
import static com.google.common.collect.FluentIterable.from

@CompileStatic
abstract class HeatMover extends HeatingObject {

    void transferHeatWithConnectedComponents(int maxTransferredHeat) {
        def heatingConnectedComponents = from(connectedComponents).filter(HeatingObject)

        double averageDurabilityLeft = average(heatingConnectedComponents*.durabilityLeft + durabilityLeft);
        heatingConnectedComponents.each {component ->
            int heatToTransfer = (int) (component.maxHeat * (1 - averageDurabilityLeft) - component.heat);
            if (heatToTransfer < 0) {
                heat += component.removeHeat(bound(heatToTransfer.abs()).toAtMost(maxTransferredHeat));
            }
            else {
                int heatTransferred = bound heatToTransfer toAtMost maxTransferredHeat
                component.putHeat(heatTransferred);
                heat -= heatTransferred;
            }
        }

    }

    void transferHeatWithReactor(int maxTransferredHeat) {
        double averageDurabilityLeft = (vessel.durabilityLeft + durabilityLeft) / 2;
        int heatToTransfer = (int) (vessel.maxHeat * (1 - averageDurabilityLeft) - vessel.heat);

        if (heatToTransfer < 0) {
            heat += vessel.removeHeat(bound(heatToTransfer.abs()).toAtMost(maxTransferredHeat))
        }
        else {
            int heatTransferred = bound heatToTransfer toAtMost maxTransferredHeat
            vessel.putHeat(heatTransferred);
            heat -= heatTransferred;
        }
    }
}
