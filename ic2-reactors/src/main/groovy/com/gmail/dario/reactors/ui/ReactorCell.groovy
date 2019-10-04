package com.gmail.dario.reactors.ui;

import static com.gmail.dario.reactors.ui.ReactorComponentMapper.EMPTY_CELL;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;

class ReactorCell extends Composite<Div> {

    int row
    int column
    int componentId;
    Image image;

    ReactorCell(int row, int column, int componentId) {
        this.row = row
        this.column = column
        this.componentId = componentId
        ReactorComponentMapper mappedComponent = ReactorComponentMapper[componentId]
        if (mappedComponent == EMPTY_CELL) {
            image = new Image();
        }
        else {
            image = new Image("/images/" + mappedComponent.getImage(), mappedComponent.getDescription());
        }
        image.width = "32px";
        image.height = "32px";
        content.add(image);
    }
}