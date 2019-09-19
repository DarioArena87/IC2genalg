package com.gmail.dario.spring.pagecomponents;

import java.util.List;
import java.util.Optional;

import com.gmail.dario.spring.reactor.BuildReactor;
import com.gmail.dario.spring.reactor.ComponentMapper;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class ReactorGrid extends VerticalLayout {

    private static final long serialVersionUID = 2049530660506470361L;

    ComponentMapper mapper = BuildReactor.getComponentMapper();

    public ReactorGrid(final List<Integer> reactorComponents, final int rows, final int columns) {
        final VerticalLayout grid = new VerticalLayout();
        for (int x = 0; x < rows; x++) {
            final HorizontalLayout row = new HorizontalLayout();
            for (int y = 0; y < columns; y++) {
                final String componentImage = Optional.ofNullable(mapper.getComponentImage(reactorComponents.get(x + y)))
                                                      .orElse(null);

                final Image image = Optional.ofNullable(componentImage)
                                            .map(it -> new Image("/images/" + it, ""))
                                            .orElseGet(() -> new Image());
                image.setWidth("36px");
                image.setHeight("36px");
                row.add(image);
            }
            grid.add(row);
        }
        add(grid);
    }
}
