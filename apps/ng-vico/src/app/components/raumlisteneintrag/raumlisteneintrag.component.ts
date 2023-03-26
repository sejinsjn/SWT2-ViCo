import { Component, Input } from '@angular/core';

import { Raum } from '../../models/Raum';

@Component({
  selector: 'swt2-raumlisteneintrag',
  templateUrl: './raumlisteneintrag.component.html',
  styleUrls: ['./raumlisteneintrag.component.scss'],
})
export class RaumlisteneintragComponent {
  @Input() raum!: Raum;
}
