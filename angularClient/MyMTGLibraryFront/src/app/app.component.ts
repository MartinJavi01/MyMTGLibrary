import { Component, OnInit, Renderer2 } from '@angular/core';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit{
  title = 'MyMTGLibraryFront';

  constructor(private renderer: Renderer2) {}

  ngOnInit(): void {
    var wallpaperNum = Math.floor(Math.random() * (10 - 0 + 1));
    this.renderer.setStyle(document.body, 'background-image', "url(\"wallpapers/mtg-wallpaper-" + wallpaperNum + ".jpg\")");
    this.renderer.setStyle(document.body, 'background-size', "contain");
  }
}
