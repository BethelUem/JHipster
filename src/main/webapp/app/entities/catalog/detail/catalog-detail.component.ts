import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICatalog } from '../catalog.model';

@Component({
  selector: 'jhi-catalog-detail',
  templateUrl: './catalog-detail.component.html',
})
export class CatalogDetailComponent implements OnInit {
  catalog: ICatalog | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ catalog }) => {
      this.catalog = catalog;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
