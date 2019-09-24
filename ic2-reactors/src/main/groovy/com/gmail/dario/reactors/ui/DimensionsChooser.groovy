package com.gmail.dario.reactors.ui

import com.vaadin.flow.component.Composite
import com.vaadin.flow.component.Text
import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import com.vaadin.flow.component.textfield.NumberField

import groovy.transform.CompileStatic

class DimensionsChooser extends Composite<HorizontalLayout> {

    private static final long serialVersionUID = -6602165337690949443L
    int rows = 6
    int columns = 9

    DimensionsChooser() {
        content.add(new Text("Size"))

        content.add new NumberField(hasControls: true, step: 1.0, value: rows).tap {
            addValueChangeListener{e -> rows = e.value.intValue()}
        }

        content.add("x")

        content.add new NumberField(hasControls: true, step: 1.0, value: columns).tap {
            addValueChangeListener{e -> columns = e.value.intValue()}
        }
    }
}
