import { html, svg, css, LitElement } from "lit-element";
import { unsafeHTML } from "lit-html/directives/unsafe-html.js";

import { repeat } from "lit-html/directives/repeat";
import { roomData } from "./dummy-data";
import "@polymer/paper-tooltip";
import "./components/room-info";

class FloorplanView extends LitElement {
  static get styles() {
    return css`
      :host {
        display: block;
        position: relative;
        width: 90%;
        margin: 5%;
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
      paper-tooltip {
        --paper-tooltip-text-color: var(--lumo-body-text-color);
        --paper-tooltip-background: var(--lumo-base-color);
        --paper-tooltip-opacity: 1;
        --paper-tooltip-duration-in: 200ms;
      }
    `;
  }
  render() {
    return html`
      <paper-tooltip
        id="tooltip"
        animation-delay="0"
        manual-mode
        fit-to-visible-bounds
        ><room-info id="roominfo"></room-info
        ><span style="visibility:hidden">a</span></paper-tooltip
      >
      ${svg`
        <svg class="floorplan" viewbox="0 0 100 100">
          <foreignObject width="100" height="100">
            <object id="floorplanobject" data="/floorplan.svg" type="image/svg+xml" @load="${e => this.updateRoomColors()}">
            </object>
          </foreignObject>
        ${repeat(
          this.roomData,
          room => room.sensorId,
          room => svg`
          <image id="sensor-${room.sensorId}" @mouseout="${e =>
            this.out(e)}" @mouseover="${e => this.hover(e, room)}" x="${
            room.floorplan.x
          }" y="${room.floorplan.y}" width="5" height="5"
         href="floorplan-marker.svg">
         </image>
          `
        )}
        </svg>
        `}
    `;
  }
  constructor() {
    super();
    this.roomData = roomData;
  }
  updateRoomColors() {
    this.roomData.forEach(room => {
      if (!room.floorplan.roomId) {
        return;
      }

      const floorplan = this.shadowRoot.querySelector("#floorplanobject");

      const roomElement = floorplan.contentDocument.querySelector(
        `#${room.floorplan.roomId}`
      );
      if (!roomElement) {
        return;
      }

      var style = roomElement.style;
      const alpha = Math.min(
        Math.abs(room.target - room.temperature) / 10,
        1.0
      );
      style.transition = "fill 1s";

      if (room.target < room.temperature) {
        style.fill = `rgba(255,0,0,${alpha})`;
      } else {
        style.fill = `rgba(0,0,255,${alpha})`;
      }
    });
  }
  hover(e, room) {
    const tooltip = this.shadowRoot.querySelector("#tooltip");
    tooltip.for = e.currentTarget.id;
    const roomInfo = tooltip.firstElementChild;
    roomInfo.room = room;
    tooltip.show();
  }
  out(e) {
    this.shadowRoot.querySelector("#tooltip").hide();
  }
}
customElements.define("floorplan-view", FloorplanView);
