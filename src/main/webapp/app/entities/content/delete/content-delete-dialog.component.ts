import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IContent } from '../content.model';
import { ContentService } from '../service/content.service';

@Component({
  templateUrl: './content-delete-dialog.component.html',
})
export class ContentDeleteDialogComponent {
  content?: IContent;

  constructor(protected contentService: ContentService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.contentService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
