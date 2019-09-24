package com.gmail.dario.reactors.ui;

import com.gmail.dario.reactors.components.ReactorComponent;
import com.gmail.dario.reactors.nulcearreactor.Reactor;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class ReactorGrid extends Composite<VerticalLayout> {

    private static final long serialVersionUID = -4533578265973189967L;
    private Reactor reactor;

    public ReactorGrid(final Reactor reactor) {
        this.reactor = reactor;
        updateGrid(reactor);
    }

    private void updateGrid(final Reactor reactor) {
        for (int i = 0; i < reactor.getRows(); i++) {
            final HorizontalLayout row = new HorizontalLayout();
            for (int j = 0; j < reactor.getColumns(); j++) {
                final ReactorComponent reactorComponent = reactor.getComponents().get(i).get(j);
                final int componentId = ReactorComponentMapper.getComponentId(reactorComponent);
                row.add(new ReactorCell(componentId));
            }
            getContent().add(row);
        }
    }
    
    public void update() {
        getContent().removeAll();
        updateGrid(reactor);
    }
}
