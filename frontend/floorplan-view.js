import { svg, css, LitElement } from "lit-element";
import { unsafeHTML } from "lit-html/directives/unsafe-html.js";

import floorplanSvg from "!!raw-loader!./assets/floorplan.svg";

import { repeat } from "lit-html/directives/repeat";
import { sensorData } from "./dummy-data";

class FloorplanView extends LitElement {
  static get styles() {
    return css`
      :host {
        display: block;
        position: relative;
        width: 100%;
      }
      .floorplan {
        width: 100%;
        height: auto;
      }
      .marker {
        width: 40px;
        height: 40px;
        position: absolute;
        top: 0;
        left: 0;
      }
    `;
  }
  render() {
    return svg`
        <svg class="floorplan" viewbox="0 0 100 100">
        ${unsafeHTML(floorplanSvg)}
        ${repeat(
          this.sensors,
          sensor => sensor.id,
          sensor => svg`
          <image x="${sensor.floorplan.x}" y="${sensor.floorplan.y}" width="5" height="5"
         href="floorplan-marker.svg"></image>
          `
        )}
        </svg>
        `;
  }
  constructor() {
    super();
    this.sensors = sensorData;
  }
  updated() {
    super.updated();
    this.sensors.forEach(sensor => {
      if (sensor.floorplan.room) {
        var style = this.shadowRoot.querySelector("#" + sensor.floorplan.room)
          .style;
        const alpha = Math.min(
          Math.abs(sensor.target - sensor.value) / 10,
          1.0
        );
        style.transition = "fill 1s";
        if (sensor.target < sensor.value) {
          style.fill = `rgba(255,0,0,${alpha})`;
        } else {
          style.fill = `rgba(0,0,255,${alpha})`;
        }
      }
    });
  }
}
customElements.define("floorplan-view", FloorplanView);
