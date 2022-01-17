import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { CatalogComponent } from './list/catalog.component';
import { CatalogDetailComponent } from './detail/catalog-detail.component';
import { CatalogUpdateComponent } from './update/catalog-update.component';
import { CatalogDeleteDialogComponent } from './delete/catalog-delete-dialog.component';
import { CatalogRoutingModule } from './route/catalog-routing.module';

@NgModule({
  imports: [SharedModule, CatalogRoutingModule],
  declarations: [CatalogComponent, CatalogDetailComponent, CatalogUpdateComponent, CatalogDeleteDialogComponent],
  entryComponents: [CatalogDeleteDialogComponent],
})
export class CatalogModule {}
