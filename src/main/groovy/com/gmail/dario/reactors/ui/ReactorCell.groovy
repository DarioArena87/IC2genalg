package com.gmail.dario.reactors.ui


import com.vaadin.flow.component.ComponentEventListener
import com.vaadin.flow.shared.Registration
import groovy.transform.CompileStatic
import org.vaadin.stefan.dnd.drag.DragSourceExtension
import org.vaadin.stefan.dnd.drop.DropTargetExtension

import static com.gmail.dario.reactors.ui.ReactorComponentMapper.EMPTY_CELL

import com.vaadin.flow.component.Composite
import com.vaadin.flow.component.html.Div
import com.vaadin.flow.component.html.Image

@CompileStatic
class ReactorCell extends Composite<Div> {

    int row
    int column
    int componentId
    Image image

    ReactorCell(int row, int column, int componentId, double durabilityLeft) {
        this.row = row
        this.column = column
        this.componentId = componentId
        image = getVaadinImage(ReactorComponentMapper.fromComponentId(componentId)).tap {
            width = "32px"
            height = "32px"
        }

        content.tap {
            add image
            if (durabilityLeft < 1) {
                style.set("position", "relative")
                add new DurabilityBar(durabilityLeft)
            }
        }

        DropTargetExtension.extend(content).addDropListener { it.dragSource.ifPresent fireEventOnLabelDrop }
    }

    private Image getVaadinImage(ReactorComponentMapper mappedComponent) {
        switch (mappedComponent) {
            case EMPTY_CELL: return new Image()
            default: return new Image("/images/" + mappedComponent.image, mappedComponent.description)
        }
    }

    Closure fireEventOnLabelDrop = { DragSourceExtension<ReactorComponentLabel> source ->
        ReactorComponentLabel component = source.component
        fireEvent(new InstallComponentEvent(this, component.componentId, row, column))
    }

    Registration setOnComponentLabelDropped(ComponentEventListener<InstallComponentEvent> e) {
        addListener(InstallComponentEvent, e)
    }
}
