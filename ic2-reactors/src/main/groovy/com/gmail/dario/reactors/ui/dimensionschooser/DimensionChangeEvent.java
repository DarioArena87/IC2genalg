package com.gmail.dario.reactors.ui.dimensionschooser;

import com.vaadin.flow.component.ComponentEvent;

public class DimensionChangeEvent extends ComponentEvent<DimensionsChooser> {
    private static final long serialVersionUID = -79843544512966867L;
    private final int newRows;
    private final int newColumns;

    public DimensionChangeEvent(final DimensionsChooser source, final boolean fromClient, final int newRows, final int newColumns) {
        super(source, fromClient);
        this.newRows = newRows;
        this.newColumns = newColumns;
    }

    public int getNewRows() {
        return newRows;
    }

    public int getNewColumns() {
        return newColumns;
    }

}
