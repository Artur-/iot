package org.artur.iot;

import com.vaadin.flow.component.UI;

public class Workaround {

    public static void addJSModule(UI ui, String url) {
        ui.getPage().executeJs(
                "var s = document.createElement('script');s.type='module'; s.src=$0;document.head.appendChild(s);",
                url);

    }

}
