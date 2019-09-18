import { html, css, LitElement } from "lit-element";
import { repeat } from "lit-html/directives/repeat";
import { roomData } from "./dummy-data";
import "./components/room-info";

class AllRoomsView extends LitElement {
  static get styles() {
    return css`
      :host {
        display: inline-block;
      }
    `;
  }
  static get properties() {
    return {
      roomData: {
        type: Array
      }
    };
  }
  constructor() {
    super();
    this.roomData = roomData;
  }
  render() {
    return html`
      ${repeat(
        this.roomData,
        roomData => roomData.sensorId,
        room => html`
          <div style="display: inline-block">
            <room-info .room="${room}"></room-info>
            <div>
              <vaadin-button>History</vaadin-button>
              <vaadin-button>Settings</vaadin-button>
            </div>
          </div>
        `
      )}
    `;
  }
}
customElements.define("all-rooms-view", AllRoomsView);
