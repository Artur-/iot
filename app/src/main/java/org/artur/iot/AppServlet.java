package org.artur.iot;

import javax.servlet.annotation.WebServlet;

import org.jsoup.nodes.Element;

import com.vaadin.flow.function.DeploymentConfiguration;
import com.vaadin.flow.server.BootstrapPageResponse;
import com.vaadin.flow.server.ServiceException;
import com.vaadin.flow.server.VaadinServlet;
import com.vaadin.flow.server.VaadinServletService;

@WebServlet("/*")
public class AppServlet extends VaadinServlet {

    @Override
    protected VaadinServletService createServletService(
            DeploymentConfiguration deploymentConfiguration)
            throws ServiceException {
        VaadinServletService service = new VaadinServletService(this,
                deploymentConfiguration) {
            @Override
            public void modifyBootstrapPage(BootstrapPageResponse response) {
                super.modifyBootstrapPage(response);
                Element bundle = response.getDocument().head()
                        .appendElement("script");
                bundle.attr("type", "module");
                bundle.attr("src", "/iot-bundle/VAADIN/build/index.nocache.js");
            }

        };
        service.init();
        return service;
    }
}
