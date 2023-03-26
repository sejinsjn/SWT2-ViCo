import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RaumlisteneintragComponent } from './raumlisteneintrag.component';

describe('RaumlisteneintragComponent', () => {
  let component: RaumlisteneintragComponent;
  let fixture: ComponentFixture<RaumlisteneintragComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [RaumlisteneintragComponent],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RaumlisteneintragComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
