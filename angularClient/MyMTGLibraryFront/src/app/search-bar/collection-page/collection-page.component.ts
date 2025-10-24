import { Component } from '@angular/core';

@Component({
  selector: 'app-collection-page',
  imports: [],
  templateUrl: './collection-page.component.html',
  styleUrl: './collection-page.component.css'
})
export class CollectionPageComponent {
  filtersOpened = false;

  changeFiltersOpened() {
    this.filtersOpened = !this.filtersOpened;
  }
}
