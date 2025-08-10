import { CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { map, Observable } from 'rxjs';
import { DeceasedResponse } from '../../utils/models';
import { DeceasedService } from '../../services/deceased.service';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-home',
  imports: [CommonModule, RouterLink],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss',
})
export class HomeComponent implements OnInit {
  private deceasedService = inject(DeceasedService);

  public allDeceased$!: Observable<DeceasedResponse[]>;
  public latestDeceased$!: Observable<DeceasedResponse[]>;
  public availableCities$!: Observable<string[]>;

  ngOnInit(): void {
    // Busca os deceased
    this.allDeceased$ = this.deceasedService.findAll();

    // Filtro para pegar os últimos 5 deceased adicionados no banco
    this.latestDeceased$ = this.allDeceased$.pipe(
      map((deceased) => [...deceased].filter(d => d.graveLocation).sort((a, b) => b.id - a.id).slice(0, 5))
    );

    // Pega as cidades em ordem alfabética
    this.availableCities$ = this.allDeceased$.pipe(
      map((deceasedList) => {
        const locations = deceasedList
          .map((d) => d.graveLocation)
          .filter((loc): loc is string => !!loc);

        return [...new Set(locations)].sort();
      })
    );
  }
}
