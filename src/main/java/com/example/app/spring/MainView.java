package com.example.app.spring;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;

@Route
@PWA(name = "Project Base for Vaadin Flow with Spring", shortName = "Project Base")
@Tag("main-layout")
@JsModule("./main-layout.js")
public class MainView extends Component implements HasUrlParameter<String> {

    @Override
    public void setParameter(BeforeEvent event, String parameter) {

	}

}
