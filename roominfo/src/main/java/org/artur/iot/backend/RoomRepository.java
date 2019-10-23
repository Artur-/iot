package org.artur.iot.backend;

import org.artur.iot.data.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, String> {

    public Room getByRoom(String room);
}