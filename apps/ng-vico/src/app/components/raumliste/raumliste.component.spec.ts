import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RaumlisteComponent } from './raumliste.component';

describe('RaumlisteComponent', () => {
  let component: RaumlisteComponent;
  let fixture: ComponentFixture<RaumlisteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [RaumlisteComponent],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RaumlisteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
