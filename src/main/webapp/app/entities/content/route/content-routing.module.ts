import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ContentComponent } from '../list/content.component';
import { ContentDetailComponent } from '../detail/content-detail.component';
import { ContentUpdateComponent } from '../update/content-update.component';
import { ContentRoutingResolveService } from './content-routing-resolve.service';

const contentRoute: Routes = [
  {
    path: '',
    component: ContentComponent,
    data: {
      defaultSort: 'id,asc',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ContentDetailComponent,
    resolve: {
      content: ContentRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ContentUpdateComponent,
    resolve: {
      content: ContentRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ContentUpdateComponent,
    resolve: {
      content: ContentRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(contentRoute)],
  exports: [RouterModule],
})
export class ContentRoutingModule {}
