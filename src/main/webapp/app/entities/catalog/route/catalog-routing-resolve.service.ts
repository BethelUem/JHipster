import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ICatalog, Catalog } from '../catalog.model';
import { CatalogService } from '../service/catalog.service';

@Injectable({ providedIn: 'root' })
export class CatalogRoutingResolveService implements Resolve<ICatalog> {
  constructor(protected service: CatalogService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICatalog> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((catalog: HttpResponse<Catalog>) => {
          if (catalog.body) {
            return of(catalog.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Catalog());
  }
}
