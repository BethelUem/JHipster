import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { ContentTagComponent } from './list/content-tag.component';
import { ContentTagDetailComponent } from './detail/content-tag-detail.component';
import { ContentTagUpdateComponent } from './update/content-tag-update.component';
import { ContentTagDeleteDialogComponent } from './delete/content-tag-delete-dialog.component';
import { ContentTagRoutingModule } from './route/content-tag-routing.module';

@NgModule({
  imports: [SharedModule, ContentTagRoutingModule],
  declarations: [ContentTagComponent, ContentTagDetailComponent, ContentTagUpdateComponent, ContentTagDeleteDialogComponent],
  entryComponents: [ContentTagDeleteDialogComponent],
})
export class ContentTagModule {}
