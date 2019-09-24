package com.gmail.dario.reactors.components

class EmptyCell extends ReactorComponent {

    final BigDecimal durabilityLeft = 1
    
    @Override
    void tick() { /*Does nothing */}

    @Override
    BigDecimal getDurabilityLeft() { 1 /* never destroys */}
}
