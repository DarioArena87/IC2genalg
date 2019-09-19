package com.gmail.dario.spring.evolution;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ArrayUtils;

import com.gmail.dario.spring.reactor.BuildReactor;
import com.gmail.dario.spring.reactor.Reactor;

import io.jenetics.Chromosome;
import io.jenetics.Genotype;
import io.jenetics.IntegerChromosome;
import io.jenetics.IntegerGene;
import io.jenetics.engine.Engine;
import io.jenetics.engine.EvolutionResult;
import io.jenetics.util.Factory;

public class ReactorEvolution implements Callable<List<Integer>> {

    private static final int REACTOR_ROWS = 6;
    private static final int REACTOR_COLUMNS = 9;

    private static BigDecimal eval(final Genotype<IntegerGene> genotype) {
        final List<Integer> components = new ArrayList<>();
        for (int i = 0; i < genotype.length(); i++) {
            final Chromosome<IntegerGene> chromosome = genotype.get(i);
            for (int j = 0; j < chromosome.length(); j++) {
                final IntegerGene gene = chromosome.getGene(j);
                components.add(gene.getAllele());
            }
        }

        final Reactor reactor = BuildReactor.fromComponentsId(REACTOR_ROWS, REACTOR_COLUMNS, components);

        for (int i = 0; i < 400; i++) {
            reactor.tick();
        }

        final BigDecimal explodedMultiplier = reactor.isExploded() ? BigDecimal.valueOf(Long.MIN_VALUE) : BigDecimal.ONE;

        final BigDecimal fitness = explodedMultiplier.multiply(reactor.getEu()).multiply(reactor.getHeat().negate());

        System.out.println(components.stream().map(it -> it.toString()).collect(Collectors.joining(",")) + " fitness: " + fitness.toPlainString());
        return fitness;
    }

    @Override
    public List<Integer> call() {

        final Factory<Genotype<IntegerGene>> geneFactory = Genotype.of(IntegerChromosome.of(0, 19, REACTOR_ROWS * REACTOR_COLUMNS));

        final Engine<IntegerGene, BigDecimal> engine = Engine.builder(ReactorEvolution::eval, geneFactory).build();

        final Genotype<IntegerGene> best = engine.stream().parallel().limit(100).collect(EvolutionResult.toBestGenotype());

        return Arrays.asList(ArrayUtils.toObject(best.getChromosome().as(IntegerChromosome.class).toArray()));
    }

}
