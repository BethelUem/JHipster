import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { UserProgramComponent } from './list/user-program.component';
import { UserProgramDetailComponent } from './detail/user-program-detail.component';
import { UserProgramUpdateComponent } from './update/user-program-update.component';
import { UserProgramDeleteDialogComponent } from './delete/user-program-delete-dialog.component';
import { UserProgramRoutingModule } from './route/user-program-routing.module';

@NgModule({
  imports: [SharedModule, UserProgramRoutingModule],
  declarations: [UserProgramComponent, UserProgramDetailComponent, UserProgramUpdateComponent, UserProgramDeleteDialogComponent],
  entryComponents: [UserProgramDeleteDialogComponent],
})
export class UserProgramModule {}
