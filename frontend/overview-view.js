import { html, css, LitElement } from "lit-element";
import "j-elements";
import "@vaadin/vaadin-charts";

class OverviewView extends LitElement {
  static get styles() {
    return css`
      img {
        width: 100%;
      }
      j-card {
        display: inline-block;
        width: 250px;
        height: 250px;
        vertical-align: top;
      }
      vaadin-chart {
        height: 200px;
      }
    `;
  }
  constructor() {
    super();
    this.recentMonths = ["Jun", "Jul", "Aug"];
    this.recentYears = ["Aug 2017", "Aug 2018", "Aug 2019"];
    this.thisMonthCategories = [
      1,
      2,
      3,
      4,
      5,
      6,
      7,
      8,
      9,
      10,
      11,
      12,
      13,
      14,
      15,
      16,
      17,
      18,
      19,
      20,
      21,
      22,
      23,
      24,
      25,
      26,
      27,
      28,
      29,
      30,
      31
    ];
    this.thisMonthConsumption = [
      10,
      20,
      35,
      42,
      43,
      45,
      56,
      68,
      80,
      89,
      96,
      110,
      130,
      140,
      150,
      160,
      170,
      "",
      "",
      "",
      "",
      "",
      "",
      "",
      "",
      "",
      "",
      "",
      "",
      "",
      "",
      ""
    ];
    this.thisMonthEstimates = [
      "",
      "",
      "",
      "",
      "",
      "",
      "",
      "",
      "",
      "",
      "",
      "",
      "",
      "",
      "",
      "",
      170,
      180,
      190,
      200,
      210,
      220,
      230,
      240,
      250,
      260,
      270,
      280,
      290,
      300
    ];
    this.thisMonthOptions = {
      yAxis: {
        max: 400
      }
    };
    this.lastMonthsConsumption = [250, 280, 320];
    this.lastYearsConsumption = [250, 220, 280];

    this.heatingCategories = ["Living room", "Bathroom"];
    this.heatingValues = [50, 20];
  }
  render() {
    return html`
        <div class="container">
        <j-card>
        <div slot="title">Current consumption</div>
        <h1>2 857W</h1>
        <h3>Average: 2 512W</h3>
        </j-card>
        <j-card>
        <div slot="title">Recent months</div>
        <vaadin-chart .categories='${this.recentMonths}'>
        <vaadin-chart-series type="column" title="Consumption" .values="${this.lastMonthsConsumption}">
  </vaadin-chart-series>

        </vaadin-chart>
        </j-card>
        <j-card>
        <div slot="title">Recent year</div>
        <vaadin-chart .categories='${this.recentYears}'>
        <vaadin-chart-series type="column" title="Consumption" .values="${this.lastYearsConsumption}">
      </vaadin-chart-series>

        </vaadin-chart>
        </j-card>
        <j-card>
        <div slot="title">This month</div>
        <vaadin-chart .categories='${this.thisMonthCategories}' .additionalOptions="${this.thisMonthOptions}">
        <vaadin-chart-series type="line" title="Consumption" .values="${this.thisMonthConsumption}">
      </vaadin-chart-series>
      <vaadin-chart-series type="line" title="Estimate" .values="${this.thisMonthEstimates}">
      </vaadin-chart-series>

        </vaadin-chart>
        </j-card>
        <j-card>
        <div slot="title">Currently heating</div>
        <vaadin-chart no-legend type="bar" .categories='${this.heatingCategories}'>
        <vaadin-chart-series .values="${this.heatingValues}"></vaadin-chart-series>
        </vaadin-chart>
        </div>
        `;
  }
  firstUpdated() {
    super.firstUpdated();
  }
}
customElements.define("overview-view", OverviewView);
