import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { UserProgramComponent } from '../list/user-program.component';
import { UserProgramDetailComponent } from '../detail/user-program-detail.component';
import { UserProgramUpdateComponent } from '../update/user-program-update.component';
import { UserProgramRoutingResolveService } from './user-program-routing-resolve.service';

const userProgramRoute: Routes = [
  {
    path: '',
    component: UserProgramComponent,
    data: {
      defaultSort: 'id,asc',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: UserProgramDetailComponent,
    resolve: {
      userProgram: UserProgramRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: UserProgramUpdateComponent,
    resolve: {
      userProgram: UserProgramRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: UserProgramUpdateComponent,
    resolve: {
      userProgram: UserProgramRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(userProgramRoute)],
  exports: [RouterModule],
})
export class UserProgramRoutingModule {}
