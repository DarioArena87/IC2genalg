package com.gmail.dario.reactors.ui


import com.vaadin.flow.component.html.Div

class DurabilityBar extends Div {

    DurabilityBar(double durabilityLeft) {
        height = "3px"
        width = "${(32 * durabilityLeft).intValue()}px"
        style.tap {
            set("position", "absolute")
            set("bottom", "0")
            set("left", "0")
            set("background", "green")
        }
    }

}
