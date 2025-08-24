import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
@Injectable({
  providedIn: 'root'
})

export class CardDetailsPageComponentService {
    constructor(private http: HttpClient) {}

    getCardByName(name: string): Observable<any> {
        return this.http.get("/details/name/" + name);
    }
}