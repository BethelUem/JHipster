import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IUserProgram } from '../user-program.model';

@Component({
  selector: 'jhi-user-program-detail',
  templateUrl: './user-program-detail.component.html',
})
export class UserProgramDetailComponent implements OnInit {
  userProgram: IUserProgram | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ userProgram }) => {
      this.userProgram = userProgram;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
