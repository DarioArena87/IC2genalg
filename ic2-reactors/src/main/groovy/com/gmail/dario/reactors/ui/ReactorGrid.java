package com.gmail.dario.reactors.ui;

import com.gmail.dario.reactors.components.ReactorComponent;
import com.gmail.dario.reactors.nulcearreactor.Reactor;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class ReactorGrid extends Composite<VerticalLayout> {

    private static final long serialVersionUID = -4533578265973189967L;
    private Reactor reactor;
    
    public ReactorGrid(Reactor reactor) {
        this.reactor = reactor;
        update();
    }

    public void update() {
        getContent().removeAll();
        for (int i = 0; i < reactor.getRows(); i++) {
            final HorizontalLayout row = new HorizontalLayout();
            for (int j = 0; j < reactor.getColumns(); j++) {
                final ReactorComponent reactorComponent = reactor.getComponents().get(i).get(j);
                row.add(new ReactorCell(ReactorComponentMapper.getComponentId(reactorComponent)));
            }
            getContent().add(row);
        }
    }
}
