import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { ContentComponent } from './list/content.component';
import { ContentDetailComponent } from './detail/content-detail.component';
import { ContentUpdateComponent } from './update/content-update.component';
import { ContentDeleteDialogComponent } from './delete/content-delete-dialog.component';
import { ContentRoutingModule } from './route/content-routing.module';

@NgModule({
  imports: [SharedModule, ContentRoutingModule],
  declarations: [ContentComponent, ContentDetailComponent, ContentUpdateComponent, ContentDeleteDialogComponent],
  entryComponents: [ContentDeleteDialogComponent],
})
export class ContentModule {}
