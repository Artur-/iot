import { html, css, LitElement } from "lit-element";
import { Router } from "@vaadin/router";

import "@vaadin/vaadin-app-layout";
import "@vaadin/vaadin-app-layout/vaadin-drawer-toggle";

import "@vaadin/vaadin-tabs";
import "@vaadin/vaadin-icons";
import "@vaadin/vaadin-grid";

import "./overview-view";
import "./settings-view";
import "./report-view";
import "./floorplan-view";
import "./all-sensors-view";

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
        <vaadin-drawer-toggle slot="navbar touch-optimized"></vaadin-drawer-toggle>
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
            <a href="overview">
              <iron-icon icon="vaadin:home"></iron-icon>
              Overview
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
            <a href="all-sensors">
              <iron-icon icon="vaadin:stock"></iron-icon>
              All sensors
            </a>
          </vaadin-tab>
          <vaadin-tab>
            <a href="settings">
              <iron-icon icon="vaadin:cogs"></iron-icon>
              Settings
            </a>
          </vaadin-tab>
        </vaadin-tabs>

        <div class="content"></div>
      </vaadin-app-layout>
    `;
  }
  firstUpdated() {
    super.firstUpdated();
    new Router(this.shadowRoot.querySelector(".content")).setRoutes([
      { path: "overview", component: "overview-view" },
      { path: "settings", component: "settings-view" },
      { path: "report", component: "report-view" },
      { path: "floorplan", component: "floorplan-view" },
      { path: "all-sensors", component: "all-sensors-view" }
    ]);
  }
}

customElements.define("main-layout", MainLayout);
