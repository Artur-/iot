package org.artur.iot.roominfo;

import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;

import javax.annotation.PostConstruct;

import com.vaadin.flow.component.WebComponentExporter;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.ChartType;
import com.vaadin.flow.component.charts.model.Configuration;
import com.vaadin.flow.component.charts.model.ListSeries;
import com.vaadin.flow.component.charts.model.Series;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.webcomponent.WebComponent;

import org.artur.iot.backend.RoomRepository;
import org.artur.iot.component.JCard;
import org.artur.iot.data.Room;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@CssImport("./room-info.css")
class RoomInfo extends Div {

    @Autowired
    private RoomRepository repo;

    public static class Exporter extends WebComponentExporter<RoomInfo> {

        public Exporter() {
            super("room-info");
            addProperty("room", "-1").onChange((roominfo, roomId) -> {
                if ("-1".equals(roomId))
                    return;
                roominfo.setRoom(roomId);
            });
        }

        @Override
        protected void configureInstance(WebComponent<RoomInfo> webComponent, RoomInfo component) {

        }

    }

    private NumberFormat format = DecimalFormat.getNumberInstance();
    private JCard card;
    private ListSeries series;
    private ListSeries targetSeries;
    private Div target;
    private Div temperature;
    private Chart chart;
    {
        format.setMaximumFractionDigits(1);
        format.setMinimumFractionDigits(1);
    }

    public RoomInfo() {
        setClassName("room-info");
        getStyle().set("display", "inline-block");
        card = new JCard("");
        temperature = new Div();
        card.add(temperature);

        target = new Div();
        card.add(target);

        chart = new Chart(ChartType.LINE);
        chart.getStyle().set("--vaadin-charts-color-1", "#ff8d008c");
        chart.getConfiguration().getLegend().setEnabled(false);
        series = new ListSeries();
        chart.getConfiguration().addSeries(series);

        targetSeries = new ListSeries();
        chart.getConfiguration().addSeries(targetSeries);

        card.add(chart);
        add(card);
    }

    public RoomInfo(Room room) {
        this();
        setRoom(room);
    }

    private void setRoom(String roomId) {
        setRoom(repo.getByRoom(roomId));
    }

    public void setRoom(Room room) {
        card.setTitle(room.getRoom());

        temperature.setText("Temperature: " + temp(room.getTemperature()));
        target.setText("Target: " + temp(room.getTarget()));

        Number[] values = room.getHistory().toArray(new Number[room.getHistory().size()]);
        series.setData(values);
        Number[] targetValues = new Number[room.getHistory().size()];
        Arrays.fill(targetValues, room.getTarget());
        targetSeries.setData(targetValues);
        Workaround.fireUpdate(series);
        Workaround.fireUpdate(targetSeries);

    }

    private String temp(double number) {
        return format.format(number) + " °C";
    }
}
