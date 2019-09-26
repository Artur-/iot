import { html, svg, LitElement } from "lit-element";

import { repeat } from "lit-html/directives/repeat";

class FloorplanSvg extends LitElement {
  static get properties() {
    return {
      svgUrl: String,
      markerSvgUrl: String,
      rooms: Array
    };
  }
  constructor() {
    super();
    this.rooms = [];
    this.svgUrl = "";
    this.markerSvgUrl = "";
  }
  createRenderRoot() {
    return this;
  }
  render() {
    return svg`
        <svg class="floorplansvg" viewbox="0 0 100 100">
          <foreignObject width="100" height="100">
            <object id="floorplanobject" data="${
              this.svgUrl
            }" type="image/svg+xml" @load="${e => this.updateRoomColors()}">
            </object>
          </foreignObject>
        ${repeat(
          this.rooms,
          room => room.id,
          room => svg`
          <image id="sensor-${room.id}" 
          @mouseout="${e => this.$server.out(room.id)}" 
          @mouseover="${e => this.$server.hover(room.id)}" 
          x="${room.x}" y="${room.y}" width="5" height="5"
         href="${this.markerSvgUrl}">
         </image>
          `
        )}
        </svg>
    `;
  }

  updateRoomColors() {
    this.rooms.forEach(room => {
      const floorplan = this.querySelector("#floorplanobject");

      const roomElement = floorplan.contentDocument.querySelector(
        `#${room.id}`
      );
      if (!roomElement) {
        return;
      }

      var style = roomElement.style;
      style.transition = "fill 1s";
      style.fill = room.style;
    });
  }
}
customElements.define("floorplan-svg", FloorplanSvg);
