import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ContentTagComponent } from '../list/content-tag.component';
import { ContentTagDetailComponent } from '../detail/content-tag-detail.component';
import { ContentTagUpdateComponent } from '../update/content-tag-update.component';
import { ContentTagRoutingResolveService } from './content-tag-routing-resolve.service';

const contentTagRoute: Routes = [
  {
    path: '',
    component: ContentTagComponent,
    data: {
      defaultSort: 'id,asc',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ContentTagDetailComponent,
    resolve: {
      contentTag: ContentTagRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ContentTagUpdateComponent,
    resolve: {
      contentTag: ContentTagRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ContentTagUpdateComponent,
    resolve: {
      contentTag: ContentTagRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(contentTagRoute)],
  exports: [RouterModule],
})
export class ContentTagRoutingModule {}
