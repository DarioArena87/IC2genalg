package com.gmail.dario.reactors.ui;

import org.apache.commons.lang3.StringUtils;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;

public class ReactorCell extends Composite<Div> {

    private static final long serialVersionUID = -2929435593880247312L;
    int componentId;
    Image image;

    public ReactorCell(final int componentId, final String componentImage) {
        image = new Image("/images/" + componentImage, StringUtils.EMPTY);
        image.setWidth("32px");
        image.setHeight("32px");
        getContent().add(image);
        this.componentId = componentId;
    }
}
