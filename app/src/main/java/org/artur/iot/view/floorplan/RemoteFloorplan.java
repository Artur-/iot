package org.artur.iot.view.floorplan;

import org.artur.iot.MainLayout;
import org.artur.iot.workaround.Workaround;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.router.Route;

@Route(value = "floorplan", layout = MainLayout.class)
@Tag("floorplan-view")
public class RemoteFloorplan extends Component implements HasSize {
    // The view is exported in the floorplan module

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        Workaround.addJSModule(attachEvent.getUI(),
                "/iot-floorplan/web-component/floorplan-view.js");
        setWidth("100%");
    }

}
