package com.gmail.dario.spring.reactor

import com.gmail.dario.spring.reactor.components.Component
import com.gmail.dario.spring.reactor.interfaces.TickListener

class Slot implements TickListener {

    Component component

    def install(Component component) {
        this.component = component
    }

    def removeComponent() {
        component = null
    }

    @Override
    void tick() {
        component?.tick()
    }
}
