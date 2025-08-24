import { Injectable } from "@angular/core";
import { MTGCard } from "../models/MTGCard";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";

@Injectable({
    providedIn: 'root'
})

export class SearchBarService {

    constructor(private httpClient: HttpClient) {}

    performCardSearch(name: String): Observable<MTGCard> {
        return this.httpClient.get("/details/name/" + name) as Observable<MTGCard>;
    }

}
