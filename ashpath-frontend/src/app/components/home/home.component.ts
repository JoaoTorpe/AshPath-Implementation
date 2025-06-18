import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  imports: [],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent implements OnInit {
    message: string = 'Welcome to AshPath Home Page!';

    constructor() { }

    ngOnInit(): void {
    }
}
