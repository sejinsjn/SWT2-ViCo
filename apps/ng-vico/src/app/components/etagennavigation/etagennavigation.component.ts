import { Component, EventEmitter, OnInit, Output } from '@angular/core';

@Component({
  selector: 'swt2-etagennavigation',
  templateUrl: './etagennavigation.component.html',
  styleUrls: ['./etagennavigation.component.scss'],
})
export class EtagennavigationComponent implements OnInit {
  @Output() etageEvent = new EventEmitter<number>();

  etagen: Map<number, string> = new Map([
    [0, 'EG'],
    [1, '1.OG'],
    [2, '2.OG'],
    [3, '3.OG'],
  ]);

  selectedEtage = 0;

  ngOnInit(): void {
    this.etageEvent.emit(this.selectedEtage);
  }
}
