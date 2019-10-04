package org.artur.iot.view.allrooms;

import javax.annotation.PostConstruct;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;

import org.artur.iot.MainLayout;
import org.artur.iot.RemoteRoomInfo;
import org.artur.iot.backend.RoomRepository;
import org.artur.iot.data.Room;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "all-rooms", layout = MainLayout.class)
public class AllRooms extends Div {

    @Autowired
    private RoomRepository repo;

    @PostConstruct
    public void init() {
        for (Room room : repo.findAll()) {
            add(new RemoteRoomInfo(room));
        }
    }
}
