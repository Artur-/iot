package org.artur.iot.view.dashboard;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.annotation.PostConstruct;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.ChartType;
import com.vaadin.flow.component.charts.model.Configuration;
import com.vaadin.flow.component.charts.model.ListSeries;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

import org.artur.iot.MainLayout;
import org.artur.iot.backend.RoomRepository;
import org.artur.iot.component.JCard;
import org.artur.iot.data.Room;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@RouteAlias(value = "", layout = MainLayout.class)
@Route(value = "dashboard", layout = MainLayout.class)
@CssImport("./dashboard.css")
public class Dashboard extends Div {

    private int currentConsumption;
    private String[] recentMonthsCategories;
    private Number[] recentMonthsValues;
    private String[] recentYearsCategories;
    private Number[] recentYearsValues;
    private String[] thisMonthCategories;
    private Double[] thisMonthValues;
    private Double[] thisMonthEstimates;
    private String[] heatingCategories;
    private Integer[] heatingValues;

    @Autowired
    private RoomRepository repo;

    @PostConstruct
    public void init() {
        setClassName("dashboard");
        this.recentMonthsCategories = new String[] { "Jun", "Jul", "Aug" };
        this.recentMonthsValues = new Number[] { 560, 850, 722 };
        this.recentYearsCategories = new String[] { "Sep 2017", "Sep 2018", "Sep 2019" };
        this.recentYearsValues = new Number[] { 560 * 12, 512 * 12, 300 * 12 };

        int daysInMonth = YearMonth.now().lengthOfMonth();
        int dayOfMonth = LocalDate.now().getDayOfMonth();

        this.thisMonthCategories = (String[]) IntStream.range(1, daysInMonth + 1).boxed().map(i -> "" + i)
                .toArray(String[]::new);
        this.thisMonthValues = new Double[daysInMonth];
        for (int i = 0; i < dayOfMonth; i++) {
            thisMonthValues[i] = i * 20.0;
        }
        thisMonthEstimates = new Double[daysInMonth];

        double delta = (thisMonthValues[dayOfMonth - 1] - thisMonthValues[0]) / (dayOfMonth - 1);

        for (int i = dayOfMonth; i < daysInMonth; i++) {
            thisMonthEstimates[i] = i * delta;
        }

        List<Room> roomData = repo.findAll();
        LoggerFactory.getLogger(getClass()).warn("Found {} rooms", roomData.size());
        List<Room> heatingRooms = roomData.stream().filter(room -> room.isHeating()).collect(Collectors.toList());
        heatingCategories = heatingRooms.stream().map((room -> room.getRoom())).toArray(String[]::new);
        heatingValues = heatingRooms.stream().map((room -> room.getPower())).toArray(Integer[]::new);
        this.currentConsumption = heatingRooms.stream().map(room -> room.getPower()).reduce((prev, now) -> prev + now)
                .orElseGet(() -> 0);

        Div container = new Div();
        container.setClassName("container");

        container.add(currentConsumption());
        container.add(recentMonths());
        container.add(recentYears());
        container.add(thisMonth());
        container.add(currentlyHeating());
        add(container);

    }

    private Component currentConsumption() {
        JCard card = new JCard("Current consumption");
        H1 h1 = new H1(power(this.currentConsumption));
        card.add(h1);

        H3 h3 = new H3("Average: " + power(2512));
        card.add(h3);
        return card;
    }

    private Component recentMonths() {
        JCard card = new JCard("Recent months");

        Chart chart = new Chart(ChartType.COLUMN);
        Configuration conf = chart.getConfiguration();
        conf.getLegend().setEnabled(false);
        conf.getxAxis().setCategories(this.recentMonthsCategories);
        ListSeries series = new ListSeries();
        conf.getyAxis().setTitle("Consumption (kWh)");
        series.setData(this.recentMonthsValues);
        conf.addSeries(series);
        card.add(chart);

        return card;
    }

    private Component recentYears() {
        JCard card = new JCard("Previous years");

        Chart chart = new Chart(ChartType.COLUMN);
        Configuration conf = chart.getConfiguration();
        conf.getLegend().setEnabled(false);
        conf.getxAxis().setCategories(this.recentYearsCategories);
        ListSeries series = new ListSeries();
        conf.getyAxis().setTitle("Consumption (kWh)");
        series.setData(this.recentYearsValues);
        conf.addSeries(series);
        card.add(chart);

        return card;
    }

    private Component thisMonth() {
        JCard card = new JCard("This month");

        Chart chart = new Chart(ChartType.LINE);
        Configuration conf = chart.getConfiguration();
        conf.getxAxis().setCategories(this.thisMonthCategories);
        ListSeries series = new ListSeries();
        conf.getyAxis().setTitle("Consumption (kWh)");
        series.setData(this.thisMonthValues);
        series.setName("Consumption");
        conf.addSeries(series);

        ListSeries estimate = new ListSeries();
        estimate.setConfiguration(new Configuration());
        estimate.setName("Estimate");
        estimate.setData(this.thisMonthEstimates);
        conf.addSeries(estimate);
        card.add(chart);

        return card;
    }

    private Component currentlyHeating() {
        JCard card = new JCard("Currently heating");

        Chart chart = new Chart(ChartType.BAR);
        Configuration conf = chart.getConfiguration();
        conf.getLegend().setEnabled(false);
        conf.getxAxis().setCategories(this.heatingCategories);
        ListSeries series = new ListSeries();
        series.setData(this.heatingValues);
        conf.addSeries(series);

        card.add(chart);

        return card;
    }

    String power(int value) {
        return value + " W";
    }
}
