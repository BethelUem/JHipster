import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';

import { ICatalog, Catalog } from '../catalog.model';
import { CatalogService } from '../service/catalog.service';

@Component({
  selector: 'jhi-catalog-update',
  templateUrl: './catalog-update.component.html',
})
export class CatalogUpdateComponent implements OnInit {
  isSaving = false;

  catalogsSharedCollection: ICatalog[] = [];

  editForm = this.fb.group({
    id: [],
    code: [],
    name: [],
    description: [],
    active: [],
    createById: [],
    createdDate: [],
    lastModifiedById: [],
    lastModifiedDate: [],
    parentId: [],
  });

  constructor(protected catalogService: CatalogService, protected activatedRoute: ActivatedRoute, protected fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ catalog }) => {
      if (catalog.id === undefined) {
        const today = dayjs().startOf('day');
        catalog.createdDate = today;
        catalog.lastModifiedDate = today;
      }

      this.updateForm(catalog);

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const catalog = this.createFromForm();
    if (catalog.id !== undefined) {
      this.subscribeToSaveResponse(this.catalogService.update(catalog));
    } else {
      this.subscribeToSaveResponse(this.catalogService.create(catalog));
    }
  }

  trackCatalogById(index: number, item: ICatalog): number {
    return item.id!;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICatalog>>): void {
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

  protected updateForm(catalog: ICatalog): void {
    this.editForm.patchValue({
      id: catalog.id,
      code: catalog.code,
      name: catalog.name,
      description: catalog.description,
      active: catalog.active,
      createById: catalog.createById,
      createdDate: catalog.createdDate ? catalog.createdDate.format(DATE_TIME_FORMAT) : null,
      lastModifiedById: catalog.lastModifiedById,
      lastModifiedDate: catalog.lastModifiedDate ? catalog.lastModifiedDate.format(DATE_TIME_FORMAT) : null,
      parentId: catalog.parentId,
    });

    this.catalogsSharedCollection = this.catalogService.addCatalogToCollectionIfMissing(this.catalogsSharedCollection, catalog.parentId);
  }

  protected loadRelationshipsOptions(): void {
    this.catalogService
      .query()
      .pipe(map((res: HttpResponse<ICatalog[]>) => res.body ?? []))
      .pipe(
        map((catalogs: ICatalog[]) => this.catalogService.addCatalogToCollectionIfMissing(catalogs, this.editForm.get('parentId')!.value))
      )
      .subscribe((catalogs: ICatalog[]) => (this.catalogsSharedCollection = catalogs));
  }

  protected createFromForm(): ICatalog {
    return {
      ...new Catalog(),
      id: this.editForm.get(['id'])!.value,
      code: this.editForm.get(['code'])!.value,
      name: this.editForm.get(['name'])!.value,
      description: this.editForm.get(['description'])!.value,
      active: this.editForm.get(['active'])!.value,
      createById: this.editForm.get(['createById'])!.value,
      createdDate: this.editForm.get(['createdDate'])!.value
        ? dayjs(this.editForm.get(['createdDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      lastModifiedById: this.editForm.get(['lastModifiedById'])!.value,
      lastModifiedDate: this.editForm.get(['lastModifiedDate'])!.value
        ? dayjs(this.editForm.get(['lastModifiedDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      parentId: this.editForm.get(['parentId'])!.value,
    };
  }
}
