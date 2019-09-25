package com.example.app.spring.component;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.html.Div;

@Tag("j-card")
@NpmPackage(value = "j-elements", version = "^0.2.1")
@JsModule("j-elements/src/j-card.js")
public class JCard extends Component implements HasComponents {

    public JCard(String title) {
        Div div = new Div();
        div.setText(title);
        addToSlot("title", div);
    }

    public void addToSlot(String slot, Component component) {
        component.getElement().setAttribute("slot", slot);
        add(component);
    }

}
