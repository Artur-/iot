package org.artur.iot.view.allrooms;

import org.artur.iot.MainLayout;
import org.artur.iot.RemoteRoomInfo;
import org.artur.iot.backend.Backend;
import org.artur.iot.data.Room;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;

@Route(value = "all-rooms", layout = MainLayout.class)
public class AllRooms extends Div {

    public AllRooms() {
        for (Room room : Backend.getRoomdata()) {
            add(new RemoteRoomInfo(room));
        }
    }
}
