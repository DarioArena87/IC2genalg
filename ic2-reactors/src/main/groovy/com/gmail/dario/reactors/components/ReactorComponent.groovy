package com.gmail.dario.reactors.components

import com.gmail.dario.reactors.nulcearreactor.Reactor
import groovy.transform.CompileStatic

@CompileStatic
abstract class ReactorComponent implements TickListener {
    
    Reactor vessel
    
    List<ReactorComponent> connectedComponents = []
    
    abstract double getDurabilityLeft()
}
