import { html, css, LitElement } from "lit-element";

import "@vaadin/vaadin-dialog";
import "@vaadin/vaadin-text-field";
import "@vaadin/vaadin-text-field/vaadin-number-field";
import "@vaadin/vaadin-form-layout";

class RoomSetupDialog extends LitElement {
  static get properties() {
    return { item: { type: Array } };
  }
  static get styles() {
    return css`
      :host {
        display: flex;
        flex-direction: column;
      }
      #id {
        width: 9em;
      }
      .hor {
        display: flex;
        flex-direction: row;
        justify-content: space-between;
      }
    `;
  }
  render() {
    return html`
      <vaadin-text-field
        id="sensorId"
        label="Sensor"
        .value="${this.item.sensorId}"
      ></vaadin-text-field>
      <vaadin-text-field
        id="location"
        label="Name"
        .value="${this.item.room}"
      ></vaadin-text-field>
      <vaadin-text-field
        id="power"
        label="Power"
        .value="${this.item.power}"
      ></vaadin-text-field>
      <div class="hor">
        <vaadin-button>Cancel</vaadin-button>
        <vaadin-button theme="error">Delete</vaadin-button>
        <vaadin-button theme="primary">Save</vaadin-button>
      </div>
      <img src="" />
    `;
  }
}
customElements.define("sensor-setup-dialog", RoomSetupDialog);
