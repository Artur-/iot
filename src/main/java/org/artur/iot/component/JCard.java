package org.artur.iot.component;

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

    private Div titleDiv;

    public JCard(String title) {
        titleDiv = new Div();
        addToSlot("title", titleDiv);
        setTitle(title);
    }

    public void setTitle(String title) {
        titleDiv.setText(title);
    }

    public void addToSlot(String slot, Component component) {
        component.getElement().setAttribute("slot", slot);
        add(component);
    }

}
