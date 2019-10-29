package com.gmail.dario.reactors.ui.dimensionschooser;

import com.vaadin.flow.component.ComponentEvent;

public class DimensionChangeEvent extends ComponentEvent<DimensionsChooser> {
    private static final long serialVersionUID = -79843544512966867L;

    public DimensionChangeEvent(final DimensionsChooser source) {
        super(source, false);
    }

}
