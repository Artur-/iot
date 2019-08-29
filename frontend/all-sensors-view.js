import { html, css, LitElement } from "lit-element";
import { repeat } from "lit-html/directives/repeat";
import "j-elements";
import { sensorData } from "./dummy-data";

class AllSensorsView extends LitElement {
  static get styles() {
    return css`
      j-card {
        display: inline-block;
        width: 300px;
        height: 350px;
        vertical-align: top;
      }
      vaadin-chart {
        height: 200px;
      }
    `;
  }
  static get properties() {
    return {
      sensors: {
        type: Array
      }
    };
  }
  constructor() {
    super();
    this.sensors = sensorData;
  }
  render() {
    return html`
      ${repeat(
        this.sensors,
        sensor => sensor.id,
        sensor => html`
          <j-card>
            <div slot="title">${sensor.location}</div>
            <div>Temperature: ${sensor.value}°C</div>
            <div>Target: ${sensor.target}°C</div>
            <vaadin-chart no-legend type="line">
              <vaadin-chart-series
                .values=${sensor.history}
              ></vaadin-chart-series>
            </vaadin-chart>
            <vaadin-button>History</vaadin-button>
            <vaadin-button>Settings</vaadin-button>
          </j-card>
        `
      )}
    `;
  }
  firstUpdated() {
    super.firstUpdated();
  }
}
customElements.define("all-sensors-view", AllSensorsView);
