import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ICatalog } from '../catalog.model';
import { CatalogService } from '../service/catalog.service';

@Component({
  templateUrl: './catalog-delete-dialog.component.html',
})
export class CatalogDeleteDialogComponent {
  catalog?: ICatalog;

  constructor(protected catalogService: CatalogService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.catalogService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
