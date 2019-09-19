package com.gmail.dario.spring.pagecomponents;

import com.gmail.dario.spring.reactor.Reactor;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class ReactorStats extends VerticalLayout {

    private static final long serialVersionUID = 6555392992122480511L;
    private final Reactor reactor;
    private final TextField euGenerated = new TextField("EU");
    private final TextField heat = new TextField("Heat");

    public ReactorStats(final Reactor reactor) {
        this.reactor = reactor;
        add(euGenerated);
        add(heat);
        update();
    }

    public void update() {
        euGenerated.setValue(reactor.getEu().toPlainString());
        heat.setValue(reactor.getHeat().toPlainString() + "/" + reactor.getMaxHeat().toPlainString());
    }
}
