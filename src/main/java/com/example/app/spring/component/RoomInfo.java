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
    {
        format.setMaximumFractionDigits(1);
        format.setMinimumFractionDigits(1);
    }

    public RoomInfo(Room room) {
        setClassName("room-info");
        getStyle().set("display", "inline-block");
        JCard card = new JCard(room.getRoom());
        Div temperature = new Div();
        temperature.setText("Temperature: " + temp(room.getTemperature()));
        card.add(temperature);

        Div target = new Div();
        target.setText("Target: " + temp(room.getTarget()));
        card.add(target);

        Chart chart = new Chart(ChartType.LINE);
        chart.getStyle().set("--vaadin-charts-color-1", "#ff8d008c");
        chart.getConfiguration().getLegend().setEnabled(false);
        Number[] values = room.getHistory()
                .toArray(new Number[room.getHistory().size()]);
        ListSeries series = new ListSeries(values);
        chart.getConfiguration().addSeries(series);

        Number[] targetValues = new Number[room.getHistory().size()];
        Arrays.fill(targetValues, room.getTarget());
        ListSeries targetSeries = new ListSeries(targetValues);
        chart.getConfiguration().addSeries(targetSeries);

        card.add(chart);
        add(card);
    }

    private String temp(double number) {
        return format.format(number) + " °C";
    }
}
