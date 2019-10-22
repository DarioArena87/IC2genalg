package com.gmail.dario.reactors.utils

import groovy.transform.CompileStatic

@CompileStatic
class Bounder {

	final int number
	
	private Bounder(int number) {
		this.number = number
	}
	
	static Bounder bound(int number) {
		new Bounder(number)
	}

    int toAtMost(int upperLimit) {
		Math.min(number, upperLimit)
	}

    int toAtLeast(int lowerLimit) {
        Math.max(lowerLimit, number)
    }

    int inRange(int lower, int higher) {
        assert lower < higher : "Invalid range (lower >= higher)"
        
        switch (number){
            case number < lower  : return lower
            case number > higher : return higher
                        default  : return number 
        }
    }
}
