import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RaumuebersichtComponent } from './raumuebersicht.component';

describe('RaumuebersichtComponent', () => {
  let component: RaumuebersichtComponent;
  let fixture: ComponentFixture<RaumuebersichtComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [RaumuebersichtComponent],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RaumuebersichtComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
