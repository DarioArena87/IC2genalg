package com.gmail.dario.reactors.ui

import com.vaadin.flow.component.ComponentEvent

class InstallComponentEvent extends ComponentEvent<ReactorCell>  {
    final int componentId
    final int row
    final int column

    InstallComponentEvent(ReactorCell source, int componentId, int row, int column) {
        super(source, false)
        this.componentId = componentId
        this.row = row
        this.column = column
    }
}
