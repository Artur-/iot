package org.artur.iot.roominfo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.vaadin.flow.component.charts.model.Configuration;
import com.vaadin.flow.component.charts.model.ListSeries;
import com.vaadin.flow.component.charts.model.Series;

public class Workaround {

    public static void fireUpdate(ListSeries series) {
        Configuration conf = series.getConfiguration();
        try {
            Method method = Configuration.class.getDeclaredMethod("fireSeriesChanged", Series.class);
            method.setAccessible(true);
            method.invoke(conf, series);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException e) {
            e.printStackTrace();
        }

    }

}
