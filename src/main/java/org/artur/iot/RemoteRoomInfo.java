package org.artur.iot;

import org.artur.iot.data.Room;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JavaScript;

@Tag("room-info")
@JavaScript("//localhost:8080/web-component/room-info.js")
// @JsModule("/otherapp/foo.js")
public class RemoteRoomInfo extends Component {

    public RemoteRoomInfo() {
    }

    public RemoteRoomInfo(Room room) {
        setRoom(room);
    }

    public void setRoom(Room room) {
        getElement().setPropertyJson("room", JsonUtil.beanToJson(room));
    }
}
