import { html, css, LitElement } from "lit-element";

import { roomData } from "../dummy-data";
import "./room-setup-dialog";

class RoomSetupView extends LitElement {
  render() {
    return html`
      <vaadin-grid selectable="true">
        <vaadin-grid-column
          path="sensorId"
          header="Sensor"
        ></vaadin-grid-column>
        <vaadin-grid-column path="room" header="Name"></vaadin-grid-column>
        <vaadin-grid-column
          path="temperature"
          header="Last reading"
        ></vaadin-grid-column>
        <vaadin-grid-column
          .renderer="${this.renderPower.bind(this)}"
          header="Power"
        ></vaadin-grid-column>
      </vaadin-grid>
      <vaadin-button @click="${e => this.addSensor()}"
        >Add room/sensor</vaadin-button
      >

      <vaadin-dialog id="dialog" .renderer="${this.renderDialog.bind(this)}">
      </vaadin-dialog>
    `;
  }
  renderPower(cell, column, data) {
    cell.innerHTML = `${data.item.power} W`;
  }
  renderDialog(container, dialog) {
    if (!container.firstElementChild) {
      container.appendChild(document.createElement("sensor-setup-dialog"));
    }
    container.firstElementChild.item = this.item;
  }
  firstUpdated() {
    super.firstUpdated();
    const grid = this.shadowRoot.querySelector("vaadin-grid");
    grid.items = roomData;
    grid.addEventListener("active-item-changed", event => {
      const item = event.detail.value;
      this.editItem(item);
    });
  }
  editItem(item) {
    this.item = item;
    this.selected = item;
    if (item) {
      grid.selectedItems = [item];
      const dialog = this.shadowRoot.querySelector("#dialog");
      dialog.opened = true;
    } else {
      grid.selectedItems = [];
    }
  }
  addSensor() {
    this.editItem({ sensorId: "", room: "" });
  }
}
customElements.define("sensor-setup-view", RoomSetupView);
