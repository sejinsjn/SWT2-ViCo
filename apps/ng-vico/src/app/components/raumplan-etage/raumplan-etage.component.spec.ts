import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RaumplanEtageComponent } from './raumplan-etage.component';

describe('RaumplanEtageComponent', () => {
  let component: RaumplanEtageComponent;
  let fixture: ComponentFixture<RaumplanEtageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [RaumplanEtageComponent],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RaumplanEtageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
