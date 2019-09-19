package com.gmail.dario.spring.reactor

class BuildReactor {

    static ComponentMapper componentMapper = new ComponentMapper()

    static Reactor fromComponentsId(final int rows, final int columns, final Iterable<Integer> componentsId) {

        new Reactor(rows, columns).tap {
            for(int row = 0; row < rows; row++) {
                for(int column = 0; column < columns; column++) {
                    installComponent(componentMapper.getComponent(componentsId[row+column]), row, column)
                }
            }
            connectComponents()
        }
    }
}
