import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { CatalogComponent } from '../list/catalog.component';
import { CatalogDetailComponent } from '../detail/catalog-detail.component';
import { CatalogUpdateComponent } from '../update/catalog-update.component';
import { CatalogRoutingResolveService } from './catalog-routing-resolve.service';

const catalogRoute: Routes = [
  {
    path: '',
    component: CatalogComponent,
    data: {
      defaultSort: 'id,asc',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CatalogDetailComponent,
    resolve: {
      catalog: CatalogRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CatalogUpdateComponent,
    resolve: {
      catalog: CatalogRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CatalogUpdateComponent,
    resolve: {
      catalog: CatalogRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(catalogRoute)],
  exports: [RouterModule],
})
export class CatalogRoutingModule {}
