package com.gmail.dario.reactors.ui;

import static com.gmail.dario.reactors.ui.ReactorComponentMapper.EMPTY_CELL;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;

public class ReactorCell extends Composite<Div> {

    private static final long serialVersionUID = -2929435593880247312L;
    int componentId;
    Image image;

    public ReactorCell(final int componentId) {
        this.componentId = componentId;
        ReactorComponentMapper mappedComponent = ReactorComponentMapper.getAt(componentId);
        if (mappedComponent == EMPTY_CELL) {
            image = new Image();
        }
        else {
            image = new Image("/images/" + mappedComponent.getImage(), mappedComponent.getDescription());
        }
        image.setWidth("32px");
        image.setHeight("32px");
        getContent().add(image);
    }
}
