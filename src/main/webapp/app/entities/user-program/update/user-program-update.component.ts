import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';

import { IUserProgram, UserProgram } from '../user-program.model';
import { UserProgramService } from '../service/user-program.service';
import { ICatalog } from 'app/entities/catalog/catalog.model';
import { CatalogService } from 'app/entities/catalog/service/catalog.service';

@Component({
  selector: 'jhi-user-program-update',
  templateUrl: './user-program-update.component.html',
})
export class UserProgramUpdateComponent implements OnInit {
  isSaving = false;

  catalogsSharedCollection: ICatalog[] = [];

  editForm = this.fb.group({
    id: [],
    user: [],
    active: [],
    createById: [],
    createdDate: [],
    lastModifiedById: [],
    lastModifiedDate: [],
    catProgram: [],
  });

  constructor(
    protected userProgramService: UserProgramService,
    protected catalogService: CatalogService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ userProgram }) => {
      if (userProgram.id === undefined) {
        const today = dayjs().startOf('day');
        userProgram.createdDate = today;
        userProgram.lastModifiedDate = today;
      }

      this.updateForm(userProgram);

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const userProgram = this.createFromForm();
    if (userProgram.id !== undefined) {
      this.subscribeToSaveResponse(this.userProgramService.update(userProgram));
    } else {
      this.subscribeToSaveResponse(this.userProgramService.create(userProgram));
    }
  }

  trackCatalogById(index: number, item: ICatalog): number {
    return item.id!;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IUserProgram>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(userProgram: IUserProgram): void {
    this.editForm.patchValue({
      id: userProgram.id,
      user: userProgram.user,
      active: userProgram.active,
      createById: userProgram.createById,
      createdDate: userProgram.createdDate ? userProgram.createdDate.format(DATE_TIME_FORMAT) : null,
      lastModifiedById: userProgram.lastModifiedById,
      lastModifiedDate: userProgram.lastModifiedDate ? userProgram.lastModifiedDate.format(DATE_TIME_FORMAT) : null,
      catProgram: userProgram.catProgram,
    });

    this.catalogsSharedCollection = this.catalogService.addCatalogToCollectionIfMissing(
      this.catalogsSharedCollection,
      userProgram.catProgram
    );
  }

  protected loadRelationshipsOptions(): void {
    this.catalogService
      .query()
      .pipe(map((res: HttpResponse<ICatalog[]>) => res.body ?? []))
      .pipe(
        map((catalogs: ICatalog[]) => this.catalogService.addCatalogToCollectionIfMissing(catalogs, this.editForm.get('catProgram')!.value))
      )
      .subscribe((catalogs: ICatalog[]) => (this.catalogsSharedCollection = catalogs));
  }

  protected createFromForm(): IUserProgram {
    return {
      ...new UserProgram(),
      id: this.editForm.get(['id'])!.value,
      user: this.editForm.get(['user'])!.value,
      active: this.editForm.get(['active'])!.value,
      createById: this.editForm.get(['createById'])!.value,
      createdDate: this.editForm.get(['createdDate'])!.value
        ? dayjs(this.editForm.get(['createdDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      lastModifiedById: this.editForm.get(['lastModifiedById'])!.value,
      lastModifiedDate: this.editForm.get(['lastModifiedDate'])!.value
        ? dayjs(this.editForm.get(['lastModifiedDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      catProgram: this.editForm.get(['catProgram'])!.value,
    };
  }
}
