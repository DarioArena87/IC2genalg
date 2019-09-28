package com.gmail.dario.reactors.ui;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Random;

import com.gmail.dario.reactors.components.ReactorComponent;
import com.gmail.dario.reactors.nulcearreactor.Reactor;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class ReactorGrid extends Composite<VerticalLayout> {

    private static final long serialVersionUID = -4533578265973189967L;
    private Reactor reactor;
    
    public ReactorGrid(final int rows, final int columns) {
        this.reactor = new Reactor(rows, columns);
        update();
    }
    
    public void setDimensions(int rows, int columns) {
        reactor.setDimensions(rows, columns);
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

    public void randomize() {
        List<ReactorComponent> randomComponents = new Random().ints(reactor.getRows() * reactor.getColumns(), 0, ReactorComponentMapper.values().length)
                                                              .boxed()
                                                              .map(i -> ReactorComponentMapper.values()[i])
                                                              .map(ReactorComponentMapper::create).collect(toList());
        
        int k = 0;
        for (int i = 0; i < reactor.getRows(); i++) {
            for (int j = 0; j < reactor.getColumns(); j++) {
                reactor.install(randomComponents.get(k++), i, j);
            }
        }
        
        update();
        
    }
}
