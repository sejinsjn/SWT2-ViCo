import { TestBed } from '@angular/core/testing';

import { RaumService } from './raum.service';

describe('RaumService', () => {
  let service: RaumService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RaumService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
