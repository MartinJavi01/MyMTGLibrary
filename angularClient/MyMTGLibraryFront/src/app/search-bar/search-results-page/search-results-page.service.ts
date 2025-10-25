import { HttpClient } from "@angular/common/http";
import { Inject, Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { ApiSearchResponse } from "../../models/ApiSearchResponse";

@Injectable({
    providedIn: 'root'
})

export class SearchResultsPageService {
    
    constructor(private httpClient: HttpClient,  @Inject('BASE_API_URL') private baseUrl: string) {
        this.baseUrl = baseUrl
    }

    searchByName(searchString: string): Observable<ApiSearchResponse> {
        return this.httpClient.get(this.baseUrl + "/search/name/" + searchString) as Observable<ApiSearchResponse>;
    }
}