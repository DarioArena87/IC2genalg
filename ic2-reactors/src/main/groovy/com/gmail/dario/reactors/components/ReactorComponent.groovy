package com.gmail.dario.reactors.components

import com.gmail.dario.reactors.nulcearreactor.Reactor

abstract class ReactorComponent implements TickListener {
    
    Reactor vessel
    
    List<ReactorComponent> connectedComponents = []
    
    abstract double getDurabilityLeft()
}
