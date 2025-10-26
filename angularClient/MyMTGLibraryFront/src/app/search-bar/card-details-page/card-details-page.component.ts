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
  cardDescription = {} as string[]

  constructor(private router: Router, private route: ActivatedRoute, private service: CardDetailsPageService) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe( params => {
      if (params['cardId'] === undefined || params['cardId'].length === 0) {
          alert("Something went wrong in the card search, try again please");
          this.router.navigate(['mtglib/home']);
        }

        this.service.searchById(params['cardId'].replaceAll(" ", "+")).subscribe( card => {
          console.log(card)
          this.currentCard = card;
          this.prepareCardData(this.currentCard)
          sessionStorage.setItem("cardName", this.currentCard.name)
          sessionStorage.setItem("currentCard", JSON.stringify(this.currentCard))
        })
    })
  }

  prepareCardData(currentCard: MTGCard) {
    document.getElementById("cardImage")?.setAttribute("src", currentCard.image_uris.border_crop)

    this.manaCosts = this.getRemovedBrackets(currentCard.mana_cost)
    this.manaCosts.length -= 1

    if (currentCard.color_identity.length > 0 && currentCard.color_identity[0].includes("!")) {
      this.colorIdentity = currentCard.color_identity[0].split("!")
    } else {
      this.colorIdentity = currentCard.color_identity
    }
    this.currentCard.color_identity.forEach((color, index) => {
      this.currentCard.color_identity[index] = "https://svgs.scryfall.io/card-symbols/" + color + ".svg"
    })

    this.cardDescription = this.getSplittedDescription(currentCard.oracle_text)
  }

  getRemovedBrackets(s: string): string[] {
    if(!s.includes("{") && !s.includes("}")) {
      var returnString = {} as string[]
      return returnString
    } else {
      var splittedString = s.split("}")
      splittedString.forEach((color, index) => {
        var finalColor = color[1]
        splittedString[index] = "https://svgs.scryfall.io/card-symbols/" + finalColor + ".svg"
      })

      return splittedString
    }
  }

  getSplittedDescription(description: string): string[] {
    var splittedDescription = description.split(".")
    splittedDescription.forEach((text, index) => {
      if(text[0] == ')' && index != 0) {
        splittedDescription[index] = text.substring(1, text.length)
        splittedDescription[index - 1] += ")"
      }
      splittedDescription[index] = splittedDescription[index] + "."
    })
    splittedDescription.length -= 1
    return splittedDescription
  }
}
