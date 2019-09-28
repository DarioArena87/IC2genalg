package com.gmail.dario.reactors.utils

import groovy.transform.CompileStatic

@CompileStatic
class Bounder {

	final BigDecimal number
	
	private Bounder(BigDecimal number) {
		this.number = number
	}
	
	static Bounder bound(BigDecimal number) {
		new Bounder(number)
	}

    BigDecimal toAtMost(BigDecimal upperLimit) {
		[number, upperLimit].min()
	}

    BigDecimal toAtLeast(BigDecimal lowerLimit) {
        [lowerLimit, number].max()
    }

    BigDecimal inRange(BigDecimal lower, BigDecimal higher) {
        assert lower < higher : "Invalid range (lower >= higher)"
        
        switch (number){
            case number < lower  : return lower
            case number > higher : return higher
                        default  : return number 
        }
    }
}
