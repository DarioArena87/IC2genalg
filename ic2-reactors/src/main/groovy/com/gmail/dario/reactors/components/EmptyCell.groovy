package com.gmail.dario.reactors.components

class EmptyCell extends ReactorComponent {

    final BigDecimal durabilityLeft = 1
    
    @Override
    void tick() { /*Does nothing */}

    @Override
    public BigDecimal getDurabilityLeft() { 1 /* never destroys */}
}
