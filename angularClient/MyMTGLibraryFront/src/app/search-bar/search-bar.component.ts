import {Component, EventEmitter, Output} from '@angular/core';
import { Router, RouterOutlet, RouterLink, ActivatedRoute } from '@angular/router';
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

  constructor(private router: Router, private route: ActivatedRoute) {}

  manageKeyboardEvent(event: KeyboardEvent) {
    if (event.key == 'Enter') {
      this.performCardSearch();
    }
  }

  performCardSearch() {
    var formattedString = this.searchString.replaceAll(" ", "")
    if(formattedString.length == 0) {
      alert("Can't perform empty search!");
      return;
    }

    this.router.navigate(['mtglib/search'], {queryParams: {searchString: formattedString}});
  }

  redirect(redirectPath: String) {
    this.router.navigate([redirectPath], { relativeTo: this.route });
  }
}
