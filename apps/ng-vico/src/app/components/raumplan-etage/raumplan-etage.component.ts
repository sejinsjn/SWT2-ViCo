import { DOCUMENT } from '@angular/common';
import {
  Component,
  Inject,
  Input,
  OnChanges,
  SimpleChanges,
} from '@angular/core';

import Zoomist from 'zoomist';

@Component({
  selector: 'swt2-raumplan-etage',
  templateUrl: './raumplan-etage.component.html',
  styleUrls: ['./raumplan-etage.component.scss'],
})
export class RaumplanEtageComponent implements OnChanges {
  @Input() selectedEtage!: number;

  zoomist!: Zoomist;

  constructor(@Inject(DOCUMENT) private document: Document) {}

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['selectedEtage']) {
      this.initRaumplan(changes['selectedEtage'].currentValue);
    }
  }

  initRaumplan(currentEtage: number): void {
    const raumplan = this.document.querySelector(`#etage_${currentEtage}`);

    this.zoomist?.destroy();

    if (raumplan) {
      this.zoomist = new Zoomist(raumplan, {
        zoomRatio: 0.3,
        height: '50%',
        slider: {
          el: '.zoomist-slider',
          direction: 'vertical',
        },
        zoomer: {
          inEl: '.zoomist-in-zoomer',
          outEl: '.zoomist-out-zoomer',
          disableOnBounds: false,
        },
      });
    }
  }
}
