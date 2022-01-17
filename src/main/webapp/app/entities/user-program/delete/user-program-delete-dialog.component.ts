import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IUserProgram } from '../user-program.model';
import { UserProgramService } from '../service/user-program.service';

@Component({
  templateUrl: './user-program-delete-dialog.component.html',
})
export class UserProgramDeleteDialogComponent {
  userProgram?: IUserProgram;

  constructor(protected userProgramService: UserProgramService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.userProgramService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
