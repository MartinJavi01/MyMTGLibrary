import {Component, Output} from '@angular/core';
import { Router, RouterOutlet, RouterLink, ActivatedRoute } from '@angular/router';
import {SearchBarService} from './search-bar.service';
import {MTGCard} from '../models/MTGCard';

@Component({
  selector: 'app-search-bar',
  imports: [RouterOutlet],
  templateUrl: './search-bar.component.html',
  styleUrl: './search-bar.component.css'
})
export class SearchBarComponent {

  @Output() currentCard = {} as MTGCard;

  constructor(private router: Router, private route: ActivatedRoute, private service: SearchBarService) {}

  performCardSearch(name: String) {
    this.service.performCardSearch(name).subscribe( card => {
      if
    })
  }

  redirect(redirectPath: String) {
    this.router.navigate([redirectPath], { relativeTo: this.route });
  }
}
