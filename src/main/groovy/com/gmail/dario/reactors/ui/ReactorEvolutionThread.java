package com.gmail.dario.reactors.ui;

import com.gmail.dario.reactors.nulcearreactor.Reactor;
import com.gmail.dario.reactors.ui.reactorevolution.ReactorGenotypeEncoder;
import com.vaadin.flow.component.UI;
import io.jenetics.Genotype;
import io.jenetics.IntegerGene;
import io.jenetics.engine.Engine;
import io.jenetics.engine.EvolutionResult;

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

        double points;
        if (reactor.isExploded()) {
            points = 0d;
        }
        else {
            points = reactor.getEu() - reactor.getEu() * reactor.getHeatPercentage();
        }

        System.out.println("Points: " + points);
        return points;
    }

    @Override
    public void run() {
        Genotype<IntegerGene> genotype = reactorEncoder.encode();

        Genotype<IntegerGene> bestGenotype = Engine.builder(this::eval, genotype)
                                                   .build()
                                                   .stream()
                                                   .limit(100)
                                                   .collect(EvolutionResult.toBestGenotype());

        ui.access(() -> {
            reactorSimulation.getReactorGrid().setReactor(reactorEncoder.decode(bestGenotype));
        });

    }

}
