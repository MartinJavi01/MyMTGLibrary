import {Component, OnInit} from '@angular/core';
import {MTGCard} from '../../models/MTGCard';
import { ActivatedRoute, Router } from '@angular/router';
import { CardDetailsPageService } from './card-details-page.service';

@Component({
  selector: 'app-card-details-page',
  templateUrl: './card-details-page.component.html',
  styleUrl: './card-details-page.component.css'
})
export class CardDetailsPageComponent implements OnInit{

  currentCard = {} as MTGCard
  manaCosts = {} as string[]
  colorIdentity = {} as string[]

  constructor(private router: Router, private route: ActivatedRoute, private service: CardDetailsPageService) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe( params => {
      if (params['id'] === undefined || params['id'].length === 0) {
          alert("Something went wrong in the card search, try again please");
          this.router.navigate(['mtglib/home']);
        }

        this.service.searchById(params['id'].replaceAll(" ", "+")).subscribe( card => {
          this.currentCard = card;
          this.prepareCardData(this.currentCard)
          sessionStorage.setItem("cardName", this.currentCard.name)
          sessionStorage.setItem("currentCard", JSON.stringify(this.currentCard))
        })
    })
  }

  prepareCardData(currentCard: MTGCard) {
    document.getElementById("cardImage")?.setAttribute("src", currentCard.image_uris.border_crop)
    this.manaCosts = currentCard.mana_cost.split("!")
    if (currentCard.color_identity[0].includes("!")) {
      this.colorIdentity = currentCard.color_identity[0].split("!")
    } else {
      this.colorIdentity = currentCard.color_identity
    }
  }
}
