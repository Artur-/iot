import { html, css, LitElement } from "lit-element";
import "j-elements";
import "@vaadin/vaadin-charts";
import { roomData } from "./dummy-data";

class DashboardView extends LitElement {
  static get styles() {
    return css`
      img {
        width: 100%;
      }
      j-card {
        display: inline-block;
        width: 300px;
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
    this.recentYears = ["Sep 2017", "Sep 2018", "Sep 2019"];
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
      20,
      40,
      65,
      82,
      103,
      125,
      146,
      168,
      180,
      209,
      216,
      230,
      240,
      255,
      270,
      280,
      300,
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
      400,
      430,
      460,
      390,
      420,
      450,
      480,
      510,
      540,
      570,
      600,
      630,
      660,
      690
    ];
    this.lastMonthsConsumption = [560, 850, 722];
    this.lastYearsConsumption = [560 * 12, 512 * 12, 300 * 12];

    const heatingRooms = roomData.filter(room => room.heating);
    this.currentConsumption = heatingRooms
      .map(room => room.power)
      .reduce((prev, now) => prev + now);
    this.heatingCategories = heatingRooms.map(room => room.room);
    this.heatingValues = heatingRooms.map(room => room.power);
  }
  render() {
    return html`
        <div class="container">
        <j-card>
        <div slot="title">Current consumption</div>
        <h1>${this.power(this.currentConsumption)}</h1>
        <h3>Average: ${this.power(2512)}</h3>
        </j-card>
        <j-card>
        <div slot="title">Recent months</div>
        <vaadin-chart .categories='${this.recentMonths}' no-legend>
        <vaadin-chart-series type="column" unit="Consumption (kWh)" .values="${
          this.lastMonthsConsumption
        }">
  </vaadin-chart-series>

        </vaadin-chart>
        </j-card>
        <j-card>
        <div slot="title">Previous years</div>
        <vaadin-chart .categories='${this.recentYears}' no-legend>
        <vaadin-chart-series type="column"  unit="Consumption (kWh)"  .values="${
          this.lastYearsConsumption
        }">
      </vaadin-chart-series>

        </vaadin-chart>
        </j-card>
        <j-card>
        <div slot="title">This month</div>
        <vaadin-chart .categories='${this.thisMonthCategories}'>
        <vaadin-chart-series type="line" title="Consumption" .values="${
          this.thisMonthConsumption
        }">
      </vaadin-chart-series>
      <vaadin-chart-series type="line" title="Estimate" .values="${
        this.thisMonthEstimates
      }">
      </vaadin-chart-series>

        </vaadin-chart>
        </j-card>
        <j-card>
        <div slot="title">Currently heating</div>
        <vaadin-chart no-legend type="bar" .categories='${
          this.heatingCategories
        }'>
        <vaadin-chart-series .values="${
          this.heatingValues
        }"></vaadin-chart-series>
        </vaadin-chart>
        </div>
        `;
  }
  firstUpdated() {
    super.firstUpdated();
  }
  power(value) {
    return value + " W";
  }
}
customElements.define("dashboard-view", DashboardView);
