import { html, css, LitElement } from "lit-element";

import { sensorData } from "./dummy-data";

class SettingsView extends LitElement {
  render() {
    return html`
      <vaadin-grid selectable="true">
        <vaadin-grid-column path="id" header="Sensor"></vaadin-grid-column>
        <vaadin-grid-column
          path="location"
          header="Location"
        ></vaadin-grid-column>
        <vaadin-grid-column
          path="value"
          header="Last reading"
        ></vaadin-grid-column>
        <vaadin-grid-column path="target" header="Target"></vaadin-grid-column>
      </vaadin-grid>
      <vaadin-button>Add sensor</vaadin-button>
    `;
  }
  firstUpdated() {
    super.firstUpdated();
    const grid = this.shadowRoot.querySelector("vaadin-grid");
    grid.items = sensorData;
    grid.addEventListener("active-item-changed", function(event) {
      const item = event.detail.value;
      grid.selectedItems = item ? [item] : [];
    });
  }
}
customElements.define("settings-view", SettingsView);
