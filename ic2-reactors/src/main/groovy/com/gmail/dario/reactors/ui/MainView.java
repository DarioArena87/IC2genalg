package com.gmail.dario.reactors.ui;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.router.Route;

@Push
@Route
class MainView extends VerticalLayout{

    private static final long serialVersionUID = 3264579859857556845L;
    
    AppMenu menu = new AppMenu();
    ReactorSimulation reactorSimulation = new ReactorSimulation();
    HorizontalLayout footer = new HorizontalLayout();

    MainView() {
        setSizeFull();
        setPadding(false);
        setSpacing(false);

        reactorSimulation.setWidth("100%");

        footer.setWidth("100%");

        add(menu, reactorSimulation, footer);

        expand(reactorSimulation);

    }
}
