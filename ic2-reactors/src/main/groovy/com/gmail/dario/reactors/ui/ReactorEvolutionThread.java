package com.gmail.dario.reactors.ui;

import com.gmail.dario.reactors.nulcearreactor.Reactor;
import com.gmail.dario.reactors.ui.reactorevolution.ReactorGenotypeEncoder;
import io.jenetics.Genotype;
import io.jenetics.IntegerGene;
import io.jenetics.engine.Engine;
import io.jenetics.engine.EvolutionResult;

public class ReactorEvolutionThread extends Thread {

    private static final double EU_WEIGHT = 0.5;
    private static final double ENDURANCE_LEFT_WEIGHT = 0.5;

    private final ReactorGenotypeEncoder reactorEncoder;
    private final int simulationTicks;

    public ReactorEvolutionThread(Reactor reactor, int simulationTicks) {
        this.reactorEncoder = new ReactorGenotypeEncoder(reactor);
        this.simulationTicks = simulationTicks;
    }

    private double eval(Genotype<IntegerGene> reactorGenotype) {
        Reactor reactor = reactorEncoder.decode(reactorGenotype);

        for (int i = 0; i < simulationTicks && !reactor.isExploded(); i++) {
            reactor.tick();
        }

        if (reactor.isExploded()) {
            return 0d;
        }

        if (reactor.getHeatPercentage() > 0) {
            return reactor.getEu() / reactor.getHeatPercentage();
        }

        return reactor.getEu();
    }

    @Override
    public void run() {
        Genotype<IntegerGene> genotype = reactorEncoder.encode();

        Genotype<IntegerGene> bestGenotype = Engine.builder(this::eval, genotype)
                                                   .build()
                                                   .stream()
                                                   .limit(100)
                                                   .collect(EvolutionResult.toBestGenotype());

    }

}
