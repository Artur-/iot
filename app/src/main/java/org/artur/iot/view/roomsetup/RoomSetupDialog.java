package org.artur.iot.view.roomsetup;

import org.artur.iot.data.Room;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.converter.StringToIntegerConverter;

public class RoomSetupDialog extends Dialog {

    private TextField sensorId;
    private Binder<Room> binder;
    private TextField room;
    private TextField power;
    private Button delete;

    public RoomSetupDialog() {
        VerticalLayout layout = new VerticalLayout();
        binder = new Binder<>(Room.class);
        sensorId = new TextField("Sensor");
        room = new TextField("Name");
        power = new TextField("Power");

        Button cancel = new Button("Cancel", e -> cancel());
        delete = new Button("Delete", e -> delete());
        delete.setThemeName("error");
        Button save = new Button("Save", e -> save());
        save.setThemeName("primary");
        layout.add(sensorId, room, power);
        layout.add(new HorizontalLayout(cancel, delete, save));
        add(layout);

        binder.forField(power)
                .withConverter(new StringToIntegerConverter("Invalid number"))
                .bind("power");
        binder.bindInstanceFields(this);
    }

    public void setItem(Room item) {
        binder.readBean(item);
        delete.setEnabled((item.getSensorId() == null));
    }

    public void cancel() {
        close();
    }

    public void delete() {
        close();
    }

    public void save() {
        close();
    }
}
