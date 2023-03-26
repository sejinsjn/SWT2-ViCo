import { Component } from '@angular/core';

import { Raum } from '../../models/Raum';
import { RaumService } from '../../services/raum.service';
import { ORDER } from '../../models/Order';
import { SORTCRITERION } from '../../models/SortCriterion';

@Component({
  selector: 'swt2-raumuebersicht',
  templateUrl: './raumuebersicht.component.html',
  styleUrls: ['./raumuebersicht.component.scss'],
})
export class RaumuebersichtComponent {
  raeumeByEtage = new Map<number, Raum[]>();

  selectedEtage!: number;

  order = ORDER.ASCENDING;

  constructor(private raumService: RaumService) {}

  sortRaeumeByCriterion(criterion: SORTCRITERION): void {
    if (criterion === SORTCRITERION.BELEGUNG) this.sortRaeumeByOccupancy();
    if (criterion === SORTCRITERION.NAME) this.sortRaeumeByName();
  }

  refreshRaeume(): void {
    this.raeumeByEtage.set(this.selectedEtage, []);

    this.raumService
      .readRaeumeByEtage(this.selectedEtage)
      .subscribe((raeume) => {
        this.raeumeByEtage.set(this.selectedEtage, raeume);
        this.sortRaeumeForAllCriteria();
      });
  }

  private sortRaeumeForAllCriteria(): void {
    this.sortRaeumeByCriterion(SORTCRITERION.NAME);
    this.sortRaeumeByCriterion(SORTCRITERION.BELEGUNG);
  }

  private sortRaeumeByOccupancy(): void {
    if (this.order === ORDER.ASCENDING) {
      this.raeumeByEtage
        .get(this.selectedEtage)
        ?.sort(
          (a, b) =>
            a.besucherAnzahl / a.maxAnzahl - b.besucherAnzahl / b.maxAnzahl
        );
    }

    if (this.order === ORDER.DESCENDING) {
      this.raeumeByEtage
        .get(this.selectedEtage)
        ?.sort(
          (a, b) =>
            b.besucherAnzahl / b.maxAnzahl - a.besucherAnzahl / a.maxAnzahl
        );
    }
  }

  private sortRaeumeByName(): void {
    if (this.order === ORDER.ASCENDING) {
      this.raeumeByEtage
        .get(this.selectedEtage)
        ?.sort((a, b) => a.raumNr.localeCompare(b.raumNr));
    }

    if (this.order === ORDER.DESCENDING) {
      this.raeumeByEtage
        .get(this.selectedEtage)
        ?.sort((a, b) => b.raumNr.localeCompare(a.raumNr));
    }
  }
}
