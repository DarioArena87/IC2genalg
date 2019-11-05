package com.gmail.dario.reactors.ui.dimensionschooser


import com.vaadin.flow.component.ComponentEventListener
import com.vaadin.flow.component.Composite
import com.vaadin.flow.component.Text
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.html.Input
import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import com.vaadin.flow.component.textfield.NumberField
import com.vaadin.flow.data.value.ValueChangeMode
import com.vaadin.flow.shared.Registration

import static com.vaadin.flow.data.value.ValueChangeMode.TIMEOUT

class DimensionsChooser extends Composite<HorizontalLayout> {

    private static final long serialVersionUID = -6602165337690949443L
    int rows = 6
    int columns = 9

    DimensionsChooser() {
        content.add(
            new Text("Size"),
            new NumberField().tap {
                hasControls = true
                pattern = "[0-9]*"
                step = 1.0
                value = rows
                addValueChangeListener {
                    rows = it.value
                    fireEvent(new DimensionChangeEvent(this))
                }
                valueChangeMode = TIMEOUT
                valueChangeTimeout = 250
            },
            new Text("x"),
            new NumberField().tap {
                hasControls = true
                pattern = "[0-9]*"
                step = 1.0
                value = columns
                addValueChangeListener {
                    columns = it.value
                    fireEvent(new DimensionChangeEvent(this))
                }
                valueChangeMode = TIMEOUT
                valueChangeTimeout = 250
            },
            new Button("Create New", { fireEvent(new DimensionChangeEvent(this)) })
        )
    }

    Registration setOnDimensionChange(ComponentEventListener<DimensionChangeEvent> e) {
        addListener(DimensionChangeEvent, e)
    }
}
