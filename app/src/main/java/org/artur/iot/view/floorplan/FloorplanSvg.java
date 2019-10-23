package org.artur.iot.view.floorplan;

import java.util.stream.Stream;

import com.vaadin.flow.component.ClientCallable;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;

import org.artur.iot.RemoteRoomInfo;
import org.artur.iot.backend.RoomRepository;
import org.artur.iot.component.PaperTooltip;
import org.artur.iot.data.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import elemental.json.Json;
import elemental.json.JsonArray;
import elemental.json.JsonObject;

@JsModule("./floorplan-svg.js")
@Tag("floorplan-svg")
@NpmPackage(value = "lit-element", version = "^2.2.1")
@org.springframework.stereotype.Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FloorplanSvg extends Component {

    private PaperTooltip tooltip;
    private RemoteRoomInfo tooltipRoominfo;

    @Autowired
    private RoomRepository repo;

    public FloorplanSvg() {

    }
    public void setInfo(PaperTooltip tooltip, RemoteRoomInfo tooltipRoomInfo) {
        this.tooltip = tooltip;
        this.tooltipRoominfo = tooltipRoomInfo;
        getElement().setProperty("svgUrl", "floorplan.svg");
        getElement().setProperty("markerSvgUrl", "floorplan-marker.svg");

        JsonArray roomsJson = Json.createArray();
        for (Room room : repo.findAll()) {
            JsonObject roomJson = Json.createObject();

            roomJson.put("id", room.getFloorplan().getRoomId());
            roomJson.put("x", room.getFloorplan().getX());
            roomJson.put("y", room.getFloorplan().getY());

            double alpha = Math.min(Math.abs(room.getTarget() - room.getTemperature()) / 10.0, 1.0);

            String style;
            if (room.getTarget() < room.getTemperature()) {
                style = "rgba(255,0,0," + alpha + ")";
            } else {
                style = "rgba(0,0,255," + alpha + ")";
            }
            roomJson.put("style", style);
            roomsJson.set(roomsJson.length(), roomJson);
        }
        getElement().setPropertyJson("rooms", roomsJson);
    }

    @ClientCallable
    public void hover(String roomId) {
        tooltip.setFor("sensor-" + roomId);

        Stream<Room> rooms = repo.findAll().stream();
        Room room = rooms.filter(r -> roomId.contentEquals(r.getFloorplan().getRoomId())).findFirst().get();
        tooltipRoominfo.setRoom(room);
        tooltip.show();

        // Ensure chart is properly sized
        tooltipRoominfo.getElement().executeJs("this.querySelector('vaadin-chart').configuration.reflow()");
    }

    @ClientCallable
    public void out(String roomId) {
        tooltip.hide();
    }

}
