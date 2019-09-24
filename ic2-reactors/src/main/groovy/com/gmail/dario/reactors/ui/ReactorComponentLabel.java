package com.gmail.dario.reactors.ui;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class ReactorComponentLabel extends HorizontalLayout {

    private static final long serialVersionUID = 2969288793107547246L;

    private Image image;

    private Text text;

    public ReactorComponentLabel(final String image, final String text) {
        this.image = new Image("/images/" + image, text);
        this.text = new Text(text);

        add(this.image, this.text);
    }

}
