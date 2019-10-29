package com.gmail.dario.reactors.ui.reactorevolution;

import com.gmail.dario.reactors.components.ReactorComponent;
import com.gmail.dario.reactors.nulcearreactor.Reactor;
import com.gmail.dario.reactors.ui.ReactorComponentMapper;
import io.jenetics.Chromosome;
import io.jenetics.Genotype;
import io.jenetics.IntegerChromosome;
import io.jenetics.IntegerGene;

import java.util.Collection;
import java.util.List;

import static com.gmail.dario.reactors.nulcearreactor.Reactor.builder;
import static java.util.stream.Collectors.toList;

public class ReactorGenotypeEncoder {

    private static final int MAX_GENE_VALUE = ReactorComponentMapper.values().length - 1;
    private static final int MIN_GENE_VALUE = 0;

    private final Reactor reactor;

    public ReactorGenotypeEncoder(Reactor reactor) {
        this.reactor = reactor;
    }

    public Genotype<IntegerGene> encode() {
        return Genotype.of(IntegerChromosome.of(getIntegerGenes()));
    }

    private Iterable<IntegerGene> getIntegerGenes() {
        return reactor.getComponents()
                      .stream()
                      .flatMap(Collection::stream)
                      .map(ReactorGenotypeEncoder::newComponentGene)
                      .collect(toList());
    }

    public Reactor decode(Genotype<IntegerGene> reactorGenotype) {
        final Reactor phenotype = builder(reactor.getRows(), reactor.getColumns()).empty();

        final Chromosome<IntegerGene> chromosome = reactorGenotype.getChromosome();
        for (int row = 0; row < reactor.getRows(); row++) {
            for (int column = 0; column < reactor.getColumns(); column++) {
                ReactorComponent component = getReactorComponent(chromosome, row, column);
                phenotype.install(component, row, column);
            }
        }
        return phenotype;
    }

    private ReactorComponent getReactorComponent(Chromosome<IntegerGene> chromosome, int row, int column) {
        IntegerGene gene = chromosome.getGene(reactor.getColumns() * row + column);
        return ReactorComponentMapper.getAt(gene.getAllele()).create();
    }

    private static IntegerGene newComponentGene(ReactorComponent component) {
        return IntegerGene.of(ReactorComponentMapper.getComponentId(component), MIN_GENE_VALUE, MAX_GENE_VALUE);
    }
}
