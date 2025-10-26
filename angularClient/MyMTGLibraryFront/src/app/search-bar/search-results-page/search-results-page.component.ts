import {Component, OnInit} from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { SearchResultsPageService } from './search-results-page.service';
import { ApiSearchResponse } from '../../models/ApiSearchResponse';

@Component ({
    selector: 'app-search-results-page',
    templateUrl: './search-results-page.component.html',
    styleUrl: './search-results-page.component.css'
})

export class SearchResultsPageComponent implements OnInit {
    
    searchString = ""
    currentResults = {} as ApiSearchResponse

    constructor(private router: Router, private route: ActivatedRoute, private service: SearchResultsPageService) {}

    ngOnInit(): void {
        this.route.queryParams.subscribe( params => {
            if (params['searchString'] === undefined || params['searchString'].length === 0) {
                alert("Something went wrong in the card search, try again please");
                this.router.navigate(['mtglib/home']);
            }

            this.searchString = params['searchString']
            this.service.searchByName(params['searchString']).subscribe(response => {
                console.log(response)
                this.currentResults = response
            })
        })
    }

    redirectToCardDetails(cardId: String) {
        this.router.navigate(['mtglib/details'], {queryParams: {cardId: cardId}});
    }

}