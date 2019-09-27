import { css, html, LitElement } from "lit-element";
import "j-elements";

class RoomInfo extends LitElement {
  static get properties() {
    return { room: Array };
  }
  static get styles() {
    return css`
      :host {
        display: inline-block;
        font-size: var(--lumo-font-size, var(--lumo-font-size-m));
        font-family: var(--lumo-font-family);
        width: 300px;
      }
      j-card {
        display: inline-block;
        width: 100%;
        vertical-align: top;
      }
      vaadin-chart {
        height: 200px;
        --vaadin-charts-color-1: #ff8d008c;
      }
    `;
  }
  constructor() {
    super();
    this.room = { room: "", temperature: 0, target: 0, history: [] };
  }
  render() {
    return html`
      <j-card>
        <div slot="title">${this.room.room}</div>
        <div>Temperature: ${this.temp(this.room.temperature)}</div>
        <div>Target: ${this.temp(this.room.target)}</div>
        <vaadin-chart no-legend type="line">
          <vaadin-chart-series
            .values=${this.room.history}
          ></vaadin-chart-series>
          <vaadin-chart-series color="red"
            .values="${Array(this.room.history.length).fill(this.room.target)}"
          ></vaadin-chart-series>
        </vaadin-chart>
      </j-card>
    `;
  }
  temp(number) {
    return Number(number).toFixed(1)+" °C";
  }
  updated() {
    super.updated();
    const charts = this.shadowRoot.querySelector("vaadin-chart");
    if (charts && charts.configuration) charts.configuration.reflow();
  }
}
customElements.define("room-info", RoomInfo);
