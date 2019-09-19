package com.gmail.dario.spring.reactor

import com.gmail.dario.spring.reactor.components.Component
import com.gmail.dario.spring.reactor.components.uraniumcells.UraniumCell
import com.gmail.dario.spring.reactor.interfaces.TickListener

import groovy.transform.CompileStatic

@CompileStatic
class Reactor implements TickListener {

    final Slot[][] slots
    BigDecimal heat = 0
    BigDecimal maxHeat = 10_000
    BigDecimal eu = 0
    boolean exploded

    Reactor(int rows = 6, int columns = 9) {
        slots = new Slot[rows][columns]

        for(int x = 0; x < rows; x++) {
            for(int y = 0; y < columns; y++) {
                slots[x][y] = new Slot()
            }
        }
    }

    void dissipateHeat(BigDecimal heatToDissipate) {
        heat = Math.max(0, (heat - heatToDissipate).intValue())
    }

    void acceptHeat(BigDecimal heatToAccept) {
        heat += heatToAccept
    }

    BigDecimal drawnHeat(BigDecimal heatToDraw) {
        BigDecimal drawnHeat = Math.min(heat.intValue(), heatToDraw.intValue())
        heat -= drawnHeat
        drawnHeat
    }

    void installComponent(Component component, int slotX, int slotY) {
        slots[slotX][slotY].install(component)
        component?.vessel = this
    }

    void connectComponents() {
        for (int x = 0; x < slots.size(); x++) {
            for(int y = 0; y < slots[x].size(); y++) {
                if (x > 1) {
                    slots[x][y].component?.connect(slots[x - 1][y].component)
                }
                if (x < slots.length - 2) {
                    slots[x][y].component?.connect(slots[x + 1][y].component)
                }
                if (y > 1) {
                    slots[x][y].component?.connect(slots[x][y - 1].component)
                }
                if (y < slots[x].length - 2) {
                    slots[x][y].component?.connect(slots[x][y + 1].component)
                }
            }
        }
    }

    @Override
    void tick() {
        if (exploded) {
            return
        }

        Closure isUraniumCell = {Slot slot -> slot.component instanceof UraniumCell}

        Collection uraniumCells = slots.collectMany { it.findAll isUraniumCell }
        uraniumCells.each { it.tick() }

        Collection otherComponents = slots.collectMany { it.findAll {!isUraniumCell} }
        otherComponents.each { it.tick() }

        if (heat == maxHeat) {
            exploded = true;
            return
        }

        for (int x = 0; x < slots.size(); x++) {
            for(int y = 0; y < slots[x].size(); y++) {
                Slot slot = slots[x][y];
                Component component = slot.component
                if (component?.heat == component?.heat) {
                    slot.removeComponent()
                }
            }
        }
    }
}
