package com.gmail.dario.reactors.ui

import com.vaadin.flow.component.Text
import com.vaadin.flow.component.html.Image
import com.vaadin.flow.component.orderedlayout.HorizontalLayout

class ReactorComponentLabel extends HorizontalLayout {

    int componentId

    ReactorComponentLabel(ReactorComponentMapper source) {
        componentId = source.id
        add(new Image("/images/" + source.image, source.description), new Text(source.description))
    }

}
