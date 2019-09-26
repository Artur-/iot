package com.example.app.spring.view;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.PropertyDescriptor;
import com.vaadin.flow.component.PropertyDescriptors;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;

@NpmPackage(value = "@polymer/paper-tooltip", version = "^3.0.1")
@JsModule("@polymer/paper-tooltip/paper-tooltip.js")
@Tag("paper-tooltip")
public class PaperTooltip extends Component implements HasStyle, HasComponents {

    public static final String CSS_TEXT_COLOR = "--paper-tooltip-text-color";
    public static final String CSS_BACKGROUND = "--paper-tooltip-background";
    public static final String CSS_OPACITY = "--paper-tooltip-opacity";
    public static final String CSS_DURATION_IN = "--paper-tooltip-duration-in";

    private static PropertyDescriptor<String, String> forAttribute = PropertyDescriptors
            .attributeWithDefault("for", "");
    private static PropertyDescriptor<Integer, Integer> animationDelayProperty = PropertyDescriptors
            .propertyWithDefault("animationDelay", 500);
    private static PropertyDescriptor<Boolean, Boolean> manualModeProperty = PropertyDescriptors
            .propertyWithDefault("manualMode", false);
    private static PropertyDescriptor<Boolean, Boolean> fitToVisibleBoundsProperty = PropertyDescriptors
            .propertyWithDefault("fitToVisibleBounds", false);

    public void setAnimationDelay(int animationDelay) {
        animationDelayProperty.set(this, animationDelay);
    }

    public int getAnimationDelay() {
        return animationDelayProperty.get(this);
    }

    public void setManualMode(boolean manualMode) {
        manualModeProperty.set(this, manualMode);
    }

    public boolean isManualMode() {
        return manualModeProperty.get(this);
    }

    public void setFitToVisibleBounds(boolean fitToVisibleBounds) {
        fitToVisibleBoundsProperty.set(this, fitToVisibleBounds);
    }

    public boolean isFitToVisibleBounds() {
        return fitToVisibleBoundsProperty.get(this);
    }

    public void setFor(String id) {
        forAttribute.set(this, id);
    }

    public void show() {
        getElement().callJsFunction("show");
    }

    public void hide() {
        getElement().callJsFunction("hide");
    }

}
