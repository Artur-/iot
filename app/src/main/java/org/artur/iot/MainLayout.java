package org.artur.iot;

import org.artur.iot.view.allrooms.AllRooms;
import org.artur.iot.view.dashboard.Dashboard;
import org.artur.iot.view.floorplan.Floorplan;
import org.artur.iot.view.roomsetup.RoomSetup;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.Tabs.Orientation;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.server.VaadinService;

@PWA(name = "Project Base for Vaadin Flow with Spring", shortName = "Project Base")
@CssImport("mainlayout.css")
public class MainLayout extends AppLayout {

    public MainLayout() {
        H1 header = new H1("In Da House");
        header.getStyle().set("margin-left", "0.5em");
        addToNavbar(true, new DrawerToggle());
        addToDrawer(header);

        Tabs tabs = new Tabs();
        tabs.add(createNavigationTab(Dashboard.class, VaadinIcon.DASHBOARD,
                "Dashboard"));
        tabs.add(createNavigationTab(Floorplan.class, VaadinIcon.THIN_SQUARE,
                "Floorplan"));
        tabs.add(createNavigationTab(AllRooms.class, VaadinIcon.STOCK,
                "All rooms"));
        tabs.add(createNavigationTab(RoomSetup.class, VaadinIcon.COGS,
                "Sensor and room setup"));
        tabs.setOrientation(Orientation.VERTICAL);
        tabs.setThemeName("minimal");
        tabs.getStyle().set("margin", "0 auto");
        tabs.getStyle().set("flex", "1");

        addToDrawer(tabs);
    }

    private Tab createNavigationTab(Class<? extends Component> targetClass,
            VaadinIcon icon, String text) {
        Tab tab = new Tab();
        RouterLink link = new RouterLink();
        link.setRoute(VaadinService.getCurrent().getRouter(), targetClass);
        link.add(icon.create(), new Text(text));
        tab.add(link);

        return new Tab(link);
    }
}
