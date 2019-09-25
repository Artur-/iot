package com.example.app.spring.view;

import com.example.app.spring.MainLayout;
import com.example.app.spring.backend.Backend;
import com.example.app.spring.component.RoomInfo;
import com.example.app.spring.data.Room;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;

@Route(value = "all-rooms", layout = MainLayout.class)
public class AllRooms extends Div {

    public AllRooms() {
        for (Room room : Backend.getRoomdata()) {
            add(new RoomInfo(room));
        }
    }
}
