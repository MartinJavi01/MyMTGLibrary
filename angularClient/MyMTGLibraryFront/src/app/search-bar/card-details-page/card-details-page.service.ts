import { HttpClient } from "@angular/common/http";
import { Inject, Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { MTGCard } from "../../models/MTGCard";

@Injectable({
    providedIn: 'root'
})

export class CardDetailsPageService {
    
    constructor(private httpClient: HttpClient,  @Inject('BASE_API_URL') private baseUrl: string) {
        this.baseUrl = baseUrl
    }

    searchById(id: String): Observable<MTGCard> {
        console.log(this.baseUrl + "/search/id/" + id);
      return this.httpClient.get(this.baseUrl + "/search/id/" + id) as Observable<MTGCard>;
    }
}