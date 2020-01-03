package com.gmail.dario.reactors.ui;

import com.gmail.dario.reactors.nulcearreactor.Reactor;
import com.gmail.dario.reactors.ui.reactorevolution.ReactorGenotypeEncoder;
import com.vaadin.flow.component.UI;
import io.jenetics.Genotype;
import io.jenetics.IntegerGene;
import io.jenetics.engine.Engine;
import io.jenetics.engine.EvolutionResult;

import java.util.List;
import java.util.stream.Collectors;

public class ReactorEvolutionThread extends Thread {

    private static final double EU_WEIGHT = 0.5;
    private static final double ENDURANCE_LEFT_WEIGHT = 0.5;

    private final UI ui;
    private final ReactorSimulation reactorSimulation;
    private final ReactorGenotypeEncoder reactorEncoder;
    private final int simulationTicks;

    public ReactorEvolutionThread(UI ui, ReactorSimulation reactorSimulation, Reactor reactor, int simulationTicks) {
        this.ui = ui;
        this.reactorSimulation = reactorSimulation;
        this.reactorEncoder = new ReactorGenotypeEncoder(reactor);
        this.simulationTicks = simulationTicks;
    }

    private double eval(Genotype<IntegerGene> reactorGenotype) {
        Reactor reactor = reactorEncoder.decode(reactorGenotype);

        for (int i = 0; i < simulationTicks && !reactor.isExploded(); i++) {
            reactor.tick();
        }

        if (reactor.isExploded()) {
            return  0d;
        }

        return reactor.getDurabilityLeft() * reactor.getEu();
    }

    @Override
    public void run() {
        Genotype<IntegerGene> genotype = reactorEncoder.encode();

        Genotype<IntegerGene> bestGenotype = Engine.builder(this::eval, genotype)
                                                   .build()
                                                   .stream()
                                                   .limit(100)
                                                   .peek(er -> setUiReactor(er.getBestPhenotype().getGenotype()))
                                                   .collect(EvolutionResult.toBestGenotype());

        setUiReactor(bestGenotype);

    }

    private void setUiReactor(Genotype<IntegerGene> genotype) {
        ui.access(() -> {
            final Reactor reactor = reactorEncoder.decode(genotype);
            reactorSimulation.setComponentList(reactor.getComponents()
                                                      .stream()
                                                      .flatMap(List::stream)
                                                      .map(ReactorComponentMapper::getComponentId)
                                                      .collect(Collectors.toList()));
            reactorSimulation.initializeSimulation();

        });
    }

}
