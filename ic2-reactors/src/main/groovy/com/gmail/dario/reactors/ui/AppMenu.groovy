package com.gmail.dario.reactors.ui

import com.vaadin.flow.component.contextmenu.MenuItem
import com.vaadin.flow.component.contextmenu.SubMenu
import com.vaadin.flow.component.menubar.MenuBar

class AppMenu extends MenuBar {

    private static final long serialVersionUID = 7450916595552306275L

    AppMenu() {
        addItem("File")
                .subMenu.with {
                    addItem("New")
                    addItem("Save")
                    addItem("Load")
                }
        
        addItem("Edit")
                .subMenu.with {
                    addItem("Settings")
                }

        openOnHover = false
        
        width = "100%"
        height = "50px"
    }
}
