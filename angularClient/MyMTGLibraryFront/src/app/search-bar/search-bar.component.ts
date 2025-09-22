import {Component, EventEmitter, Output} from '@angular/core';
import { Router, RouterOutlet, RouterLink, ActivatedRoute } from '@angular/router';
import {SearchBarService} from './search-bar.service';
import {MTGCard} from '../models/MTGCard';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-search-bar',
  imports: [RouterOutlet, FormsModule],
  templateUrl: './search-bar.component.html',
  styleUrl: './search-bar.component.css'
})
export class SearchBarComponent {

  searchString = "";

  constructor(private router: Router, private route: ActivatedRoute, private service: SearchBarService) {}

  manageKeyboardEvent(event: KeyboardEvent) {
    if (event.key == 'Enter') {
      this.performCardSearch();
    }
  }

  performCardSearch() {
    if(this.searchString.replaceAll(" ", "").length == 0) {
      alert("Can't perform empty search!");
      return;
    }

    this.service.searchByName(this.searchString.replaceAll(' ', '+'))
        .subscribe( card => {
            this.searchString = "";
            this.router.navigate(['mtglib/details'], {queryParams: {name: card.name}});
          },
          err => {
            this.service.searchById(this.searchString)
              .subscribe( card => {
                  this.searchString = "";
                  this.router.navigate(['mtglib/details']);
                },
                err => {
                  console.error(err);
                })
            console.log(err);
          })
  }

  redirect(redirectPath: String) {
    this.router.navigate([redirectPath], { relativeTo: this.route });
  }
}
