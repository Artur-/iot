import { html, css, LitElement } from 'lit-element';

import '@vaadin/vaadin-date-picker'

class ReportView extends LitElement {
    render() {
        return html`
              <vaadin-date-picker id="from" label="From"></vaadin-date-picker>
              <vaadin-date-picker id="to" label="To"></vaadin-date-picker>
              <div>
                <b>PDF report goes here</b>
              </div>
        `;
    }
    firstUpdated() {
        super.firstUpdated();
        this.shadowRoot.querySelector("#from").value = "2019-07-01";
        this.shadowRoot.querySelector("#to").value = "2019-07-31";
    }
}
customElements.define("report-view", ReportView);
