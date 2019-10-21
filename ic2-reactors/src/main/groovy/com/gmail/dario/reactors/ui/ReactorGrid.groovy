package com.gmail.dario.reactors.ui


import com.gmail.dario.reactors.nulcearreactor.Reactor
import com.vaadin.flow.component.Composite
import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import groovy.transform.CompileStatic

@CompileStatic
class ReactorGrid extends Composite<VerticalLayout> {

    private Reactor reactor;

    void setReactor(Reactor reactor){
        this.reactor = reactor;
        update();
    }

    void update() {
        content.removeAll();
        for (int i = 0; i < reactor.rows; i++) {
            final HorizontalLayout row = new HorizontalLayout();
            for (int j = 0; j < reactor.columns; j++) {
                def component = reactor.components[i][j]
                row.add(new ReactorCell(i, j, ReactorComponentMapper.getComponentId(component), component.durabilityLeft));
            }

            content.add(row);
        }
    }
}
