package com.gmail.dario.reactors.ui

import com.gmail.dario.reactors.ui.DimensionsChooser.DimensionChangeEvent
import com.vaadin.flow.component.ComponentEvent
import com.vaadin.flow.component.ComponentEventListener
import com.vaadin.flow.component.Composite
import com.vaadin.flow.component.Text
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import com.vaadin.flow.component.textfield.NumberField
import com.vaadin.flow.shared.Registration
import groovy.transform.CompileStatic
import groovy.transform.MapConstructor

class DimensionsChooser extends Composite<HorizontalLayout> {

    private static final long serialVersionUID = -6602165337690949443L
    int rows = 6
    int columns = 9

    public static class DimensionChangeEvent extends ComponentEvent<DimensionsChooser> {
        final int newRows;
        final int newColumns;
        
        DimensionChangeEvent(DimensionsChooser source, boolean fromClient, int newRows, int newColumns){
            super(source, fromClient)
            this.newRows = newRows
            this.newColumns = newColumns
        }
    }
    
    DimensionsChooser() {
        content.add(
            new Text("Size"),
            new NumberField(hasControls: true, step: 1.0, value: rows).tap { addValueChangeListener{rows = it.value}},
            new Text("x"),
            new NumberField(hasControls: true, step: 1.0, value: columns).tap { addValueChangeListener{columns = it.value} },
            new Button("Create New", { fireEvent(new DimensionChangeEvent(this, false, rows, columns)) })
        )
        
    }
    
    Registration setOnDimensionChange(ComponentEventListener<DimensionChangeEvent> e) {
        addListener(DimensionChangeEvent, e);
    }
}
