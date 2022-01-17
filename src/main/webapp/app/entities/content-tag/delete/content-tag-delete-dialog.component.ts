import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IContentTag } from '../content-tag.model';
import { ContentTagService } from '../service/content-tag.service';

@Component({
  templateUrl: './content-tag-delete-dialog.component.html',
})
export class ContentTagDeleteDialogComponent {
  contentTag?: IContentTag;

  constructor(protected contentTagService: ContentTagService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.contentTagService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
