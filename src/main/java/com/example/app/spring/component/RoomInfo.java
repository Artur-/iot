package com.example.app.spring.component;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;

import com.example.app.spring.data.Room;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.ChartType;
import com.vaadin.flow.component.charts.model.ListSeries;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;

@CssImport("room-info.css")
public class RoomInfo extends Div {
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

    public void setRoom(Room room) {
        card.setTitle(room.getRoom());

        temperature.setText("Temperature: " + temp(room.getTemperature()));
        target.setText("Target: " + temp(room.getTarget()));

        Number[] values = room.getHistory()
                .toArray(new Number[room.getHistory().size()]);
        // series.setData(values);

        Number[] targetValues = new Number[room.getHistory().size()];
        Arrays.fill(targetValues, room.getTarget());
        // targetSeries.setData(targetValues);

        series = new ListSeries(values);

        targetSeries = new ListSeries(targetValues);
//        chart.getConfiguration().setSeries(series, targetSeries);
//        chart.getConfiguration().setSeries();
        chart.getConfiguration().addSeries(series);
        chart.getConfiguration().addSeries(targetSeries);

    }

    private String temp(double number) {
        return format.format(number) + " °C";
    }
}
