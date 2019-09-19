package com.gmail.dario.spring;


import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.gmail.dario.spring.evolution.ReactorEvolution;
import com.gmail.dario.spring.pagecomponents.ReactorGrid;
import com.gmail.dario.spring.pagecomponents.ReactorStats;
import com.gmail.dario.spring.reactor.BuildReactor;
import com.gmail.dario.spring.reactor.Reactor;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

@Route
@Push
public class MainView extends VerticalLayout {

    private static final int REACTOR_ROWS = 6;
    private static final int REACTOR_COLUMNS = 9;
    private static final long serialVersionUID = -6997275108637674313L;

    public MainView() {
        final ExecutorService backGroundExecutor = Executors.newFixedThreadPool(1);

        final Future<List<Integer>> bestReactor = backGroundExecutor.submit(new ReactorEvolution());

        final Text status = new Text("Calculating...");

        add(status);

        backGroundExecutor.submit(() -> {
            try {
                final List<Integer> reactorComponents = bestReactor.get();
                VaadinSession.getCurrent().access(() -> {
                status.setText("Done");
                for (final Integer integer : reactorComponents) {
                    System.out.println(integer);
                }
                final Reactor reactor = BuildReactor.fromComponentsId(REACTOR_ROWS, REACTOR_COLUMNS, reactorComponents);

                final HorizontalLayout reactorInside = new HorizontalLayout();
                final VerticalLayout grid = new ReactorGrid(reactorComponents, REACTOR_ROWS, REACTOR_COLUMNS);
                reactorInside.add(grid);

                final ReactorStats reactorStats = new ReactorStats(reactor);
                reactorInside.add(reactorStats);
                add(reactorInside);
                });
            }
            catch (final InterruptedException e1) {
                e1.printStackTrace();
            }
            catch (final ExecutionException e1) {
                e1.printStackTrace();
            }
        });
    }

}
