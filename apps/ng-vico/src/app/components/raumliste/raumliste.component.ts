import { Component, EventEmitter, Input, Output } from '@angular/core';

import { Raum } from '../../models/Raum';
import { ORDER } from '../../models/Order';
import { SORTCRITERION } from '../../models/SortCriterion';

@Component({
  selector: 'swt2-raumliste',
  templateUrl: './raumliste.component.html',
  styleUrls: ['./raumliste.component.scss'],
})
export class RaumlisteComponent {
  @Input() selectedEtage!: number;
  @Input() raeume: Raum[] | undefined;

  @Output() orderEvent = new EventEmitter<ORDER>();
  @Output() sortCriterionEvent = new EventEmitter<SORTCRITERION>();
  @Output() refreshEvent = new EventEmitter();

  sortCriteria = [SORTCRITERION.BELEGUNG, SORTCRITERION.NAME];
  ordersBySortCriterion = new Map(
    this.sortCriteria.map((criterion) => [criterion, ORDER.ASCENDING])
  );

  sortCriterion = SORTCRITERION;

  sortByCriterion(criterion: SORTCRITERION): void {
    this.emitAndSwitchOrder(criterion);
    this.sortCriterionEvent.emit(criterion);
  }

  emitRefreshEvent(): void {
    this.refreshEvent.emit();
  }

  private emitAndSwitchOrder(criterion: SORTCRITERION): void {
    if (this.ordersBySortCriterion.get(criterion) === ORDER.ASCENDING)
      this.ordersBySortCriterion.set(criterion, ORDER.DESCENDING);
    else this.ordersBySortCriterion.set(criterion, ORDER.ASCENDING);

    this.orderEvent.emit(this.ordersBySortCriterion.get(criterion));
  }
}
