package rectorevolution

import com.gmail.dario.reactors.nulcearreactor.Reactor
import com.gmail.dario.reactors.ui.reactorevolution.ReactorGenotypeEncoder
import spock.lang.Specification

class ReactorEncodingSpec extends Specification {

    def "Gene reactor decoding and encoding"() {
        given: "a pre-built reactor"
        Reactor reactor = Reactor.builder(6, 9).random()

        when: "i encode the reactor"
        ReactorGenotypeEncoder encoder = new ReactorGenotypeEncoder(reactor)
        def genotype = encoder.encode()
        and: "i decode the reactor"
        Reactor newReactor = encoder.decode(genotype)

        then: "i get a new reactor"
        newReactor != reactor

        and: "new reactor has same values as the previous"
        newReactor.rows == reactor.rows
        newReactor.columns == reactor.columns
        [
            newReactor.components.flatten().collect { it.class },
            reactor.components.flatten().collect { it.class }
        ].transpose().every { it[0] == it[1] }
    }
}
