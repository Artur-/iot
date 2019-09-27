package org.artur.iot.view.roomsetup;

import org.artur.iot.MainLayout;
import org.artur.iot.backend.Backend;
import org.artur.iot.data.Room;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;

@Route(value = "sensor-setup", layout = MainLayout.class)
public class RoomSetup extends Div {

    private Grid<Room> grid;
    private RoomSetupDialog dialog = new RoomSetupDialog();

    public RoomSetup() {
        grid = new Grid<>(Room.class);
        grid.setColumns("sensorId", "room", "temperature");

        grid.setItems(Backend.getRoomdata());
        grid.getColumnByKey("sensorId").setHeader("Sensor");
        grid.getColumnByKey("room").setHeader("Name");
        grid.getColumnByKey("temperature").setHeader("Last reading");
        grid.addColumn(room -> room.getPower() + " W", "power")
                .setHeader("Power");
        grid.addSelectionListener(e -> {
            editSensor(grid.getSelectedItems().iterator().next());
        });

        Button add = new Button("Add room/sensor", e -> editSensor(new Room()));
        add(grid);
        add(add);
    }

    private void editSensor(Room room) {
        dialog.setItem(room);
        dialog.open();
    }

}
