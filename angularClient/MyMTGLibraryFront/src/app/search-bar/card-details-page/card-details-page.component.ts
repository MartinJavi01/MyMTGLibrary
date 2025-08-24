import {Component, Input} from '@angular/core';
import {MTGCard} from '../../models/MTGCard';

@Component({
  selector: 'app-card-details-page',
  imports: [],
  templateUrl: './card-details-page.component.html',
  styleUrl: './card-details-page.component.css'
})
export class CardDetailsPageComponent {

  @Input() card = {} as MTGCard;
  cardName = "";

  constructor() {
    this.cardName = this.card.name;
  }
}
