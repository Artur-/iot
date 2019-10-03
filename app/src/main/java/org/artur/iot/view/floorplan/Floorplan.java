package org.artur.iot.view.floorplan;

import org.artur.iot.MainLayout;
import org.artur.iot.RemoteRoomInfo;
import org.artur.iot.component.PaperTooltip;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.dom.Style;
import com.vaadin.flow.router.Route;

@Route(value = "floorplan", layout = MainLayout.class)
@CssImport("./floorplan.css")
public class Floorplan extends Div {

    public Floorplan() {
        setClassName("floorplan");
        PaperTooltip tooltip = new PaperTooltip();
        tooltip.setId("tooltip");
        tooltip.setAnimationDelay(0);
        tooltip.setManualMode(true);
        tooltip.setFitToVisibleBounds(true);
        RemoteRoomInfo tooltipRoomInfo = new RemoteRoomInfo();
        Span dummy = new Span("a");
        dummy.getStyle().set("visibility", "hidden");
        tooltip.add(tooltipRoomInfo, dummy);

        Style style = tooltip.getStyle();
        style.set(PaperTooltip.CSS_TEXT_COLOR, "var(--lumo-body-text-color)");
        style.set(PaperTooltip.CSS_BACKGROUND, "var(--lumo-base-color)");
        style.set(PaperTooltip.CSS_OPACITY, "1");
        style.set(PaperTooltip.CSS_DURATION_IN, "200ms");
        add(tooltip);

        add(new FloorplanSvg(tooltip, tooltipRoomInfo));
    }

}
