import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EtagennavigationComponent } from './etagennavigation.component';

describe('EtagennavigationComponent', () => {
  let component: EtagennavigationComponent;
  let fixture: ComponentFixture<EtagennavigationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [EtagennavigationComponent],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EtagennavigationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
