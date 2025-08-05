import { Component } from '@angular/core';
import { Router, RouterOutlet, RouterLink, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-search-bar',
  imports: [RouterOutlet],
  templateUrl: './search-bar.component.html',
  styleUrl: './search-bar.component.css'
})
export class SearchBarComponent {

  optionText: String;

  constructor(private router: Router, private route: ActivatedRoute) {
    this.optionText = 'Collection';
  }

  redirectAndChangeOptionText(){
    this.optionText = (this.optionText === 'Collection') ? 'Home' : 'Collection';
    if (this.optionText === 'Home') {
      this.router.navigate(['collection'], { relativeTo: this.route });
    } else {
      this.router.navigate(['home'], { relativeTo: this.route });
    }
  }

  redirect(redirectPath: String) {
    this.router.navigate([redirectPath], { relativeTo: this.route });
  }
}
