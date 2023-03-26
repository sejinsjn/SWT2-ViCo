import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { environment } from '../../environments/environment';

import { Raum } from '../models/Raum';

@Injectable({
  providedIn: 'root',
})
export class RaumService {
  constructor(private http: HttpClient) {}

  readRaeumeByEtage(etage: number): Observable<Raum[]> {
    return this.http.get<Raum[]>(`${environment.backendUrl}/etage/${etage}`);
  }
}
