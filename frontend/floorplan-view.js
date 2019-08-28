import { html, css, LitElement } from 'lit-element';

class FloorplanView extends LitElement {
    render() {
        return html`
                <img src="floorplan.png">
        `;
    }
    firstUpdated() {
        super.firstUpdated();
    }
}
customElements.define("floorplan-view", FloorplanView);
