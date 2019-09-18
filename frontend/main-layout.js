import { html, css, LitElement } from "lit-element";
import { Router } from "@vaadin/router";

import "@vaadin/vaadin-app-layout";
import "@vaadin/vaadin-app-layout/vaadin-drawer-toggle";

import "@vaadin/vaadin-tabs";
import "@vaadin/vaadin-icons";
import "@vaadin/vaadin-grid";

import "./dashboard-view";
import "./room-setup/room-setup-view";
import "./report-view";
import "./floorplan-view";
import "./all-rooms-view";

class MainLayout extends LitElement {
  constructor() {
    super();
  }
  static get styles() {
    return css`
      iron-icon {
        margin-right: 0.2em;
      }
    `;
  }
  render() {
    return html`
      <vaadin-app-layout>
        <vaadin-drawer-toggle
          slot="navbar touch-optimized"
        ></vaadin-drawer-toggle>
        <div slot="drawer">
          <h1 style="margin-left: 0.5em">In Da House</h1>
        </div>
        <vaadin-tabs
          slot="drawer"
          orientation="vertical"
          theme="minimal"
          style="margin: 0 auto; flex: 1;"
        >
          <vaadin-tab>
            <a href="dashboard">
              <iron-icon icon="vaadin:dashboard"></iron-icon>
              Dashboard 
            </a>
          </vaadin-tab>
          <vaadin-tab>
            <a href="floorplan">
              <iron-icon icon="vaadin:thin-square"></iron-icon>
              Floorplan
            </a>
          </vaadin-tab>
          <vaadin-tab>
            <a href="report">
              <iron-icon icon="vaadin:newspaper"></iron-icon>
              Usage report
            </a>
          </vaadin-tab>
          <vaadin-tab>
            <a href="all-rooms">
              <iron-icon icon="vaadin:stock"></iron-icon>
              All rooms
            </a>
          </vaadin-tab>
          <vaadin-tab>
            <a href="sensor-setup">
              <iron-icon icon="vaadin:cogs"></iron-icon>
              Sensor and room setup
            </a>
          </vaadin-tab>
        </vaadin-tabs>

        <div class="content"></div>
      </vaadin-app-layout>
    `;
  }
  firstUpdated() {
    super.firstUpdated();
    const router = new Router(this.shadowRoot.querySelector(".content"));
    router.setRoutes([
      { path: "dashboard", component: "dashboard-view" },
      { path: "sensor-setup", component: "sensor-setup-view" },
      { path: "report", component: "report-view" },
      { path: "floorplan", component: "floorplan-view" },
      { path: "all-rooms", component: "all-rooms-view" }
    ]);
    window.addEventListener("vaadin-router-location-changed", e => {
      const appLayout = this.shadowRoot.querySelector("vaadin-app-layout");
      if (appLayout.overlay) {
        // Close overlay on mobiles when navigating
        appLayout.drawerOpened = false;
      }
    });
  }
}

customElements.define("main-layout", MainLayout);
