import { Routes } from '@angular/router';
import { HomePageComponent } from './search-bar/home-page/home-page.component';
import { CollectionPageComponent } from './search-bar/collection-page/collection-page.component';
import { CardDetailsPageComponent } from './search-bar/card-details-page/card-details-page.component';
import { SearchBarComponent } from './search-bar/search-bar.component';

export const routes: Routes = [
    { path: 'mtglib',
        component: SearchBarComponent,
        children:[
            { path: '',  redirectTo: 'home', pathMatch: 'full'},
            { path: 'home', component: HomePageComponent},
            { path: 'details', component: CardDetailsPageComponent},
            { path: 'collection', component: CollectionPageComponent}
        ]
    }
];
