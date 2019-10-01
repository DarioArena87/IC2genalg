package com.gmail.dario.reactors.components.heatexchangers;

import static com.gmail.dario.reactors.utils.Bounder.bound;
import static com.gmail.dario.reactors.utils.FastMath.average;
import static com.google.common.collect.FluentIterable.from;

import java.util.List;

import com.gmail.dario.reactors.components.HeatingObject;

public abstract class HeatMover extends HeatingObject {

    void transferHeatWithConnectedComponents(int maxTransferredHeat) {
        List<HeatingObject> heatingConnectedComponents = from(getConnectedComponents()).filter(HeatingObject.class).toList();

        double averageDurabilityLeft = average(from(heatingConnectedComponents).transform(HeatingObject::getDurabilityLeft).append(getDurabilityLeft()).toList());
        heatingConnectedComponents.forEach(component -> {
            int heatToTransfer = (int) (component.getMaxHeat() * (1 - averageDurabilityLeft) - component.getHeat());
            if (heatToTransfer < 0) {
                setHeat(getHeat() + component.removeHeat(bound(Math.abs(heatToTransfer)).toAtMost(maxTransferredHeat)));
            }
            else {
                int heatTransferred = bound(heatToTransfer).toAtMost(maxTransferredHeat);
                component.putHeat(heatTransferred);
                setHeat(getHeat() - heatTransferred);
            }
        });

    }

    void transferHeatWithReactor(int maxTransferredHeat) {
        double averageDurabilityLeft = (getVessel().getDurabilityLeft() + getDurabilityLeft()) / 2;
        int heatToTransfer = (int) (getVessel().getMaxHeat() * (1 - averageDurabilityLeft) - getVessel().getHeat());

        if (heatToTransfer < 0) {
            setHeat(getHeat() + getVessel().removeHeat(bound(Math.abs(heatToTransfer)).toAtMost(maxTransferredHeat)));
        }
        else {
            int heatTransferred = bound(heatToTransfer).toAtMost(maxTransferredHeat);
            getVessel().putHeat(heatTransferred);
            setHeat(getHeat() - heatTransferred);
        }
    }
}
