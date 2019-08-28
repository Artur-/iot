import { html, svg, css, LitElement } from 'lit-element';
import { unsafeHTML } from 'lit-html/directives/unsafe-html.js';
import { styleMap } from 'lit-html/directives/style-map';

import floorplanSvg from "!!raw-loader!./assets/floorplan.svg";
import markerSvg from "!!raw-loader!./assets/floorplan-marker.svg";
import { repeat } from "lit-html/directives/repeat";
import { sensorData } from './dummy-data'

class FloorplanView extends LitElement {
    static get styles() {
        return css`
        :host {
            display: block;
            position: relative;
        }
        .floorplan {
            width: 100%;
            height: auto;
        }
        .marker {
            width: 40px;
            height: 40px;
            position: absolute;
            top:0;left:0;
        }
        `;
    }
    render() {
        return svg`
        ${unsafeHTML(floorplanSvg)}
        ${repeat(sensorData, sensor => sensor.id, sensor => html`
            <svg class="marker" style="${styleMap({ left: sensor.floorplan.x, top: sensor.floorplan.y })}" @click="${e => this.highlight(sensor.floorplan.room)}">
            ${unsafeHTML(markerSvg)}
            </svg>
        `)}

        `;
    }
    highlight(id) {

    }
    firstUpdated() {
        super.firstUpdated();
    }
    updated() {
        debugger;
        super.updated();
        sensorData.forEach(sensor => {
            if (sensor.floorplan.room) {
                var style = this.shadowRoot.querySelector("#" + sensor.floorplan.room).style;
                const alpha = Math.min((Math.abs(sensor.target - sensor.value) / 10), 1.0);
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
