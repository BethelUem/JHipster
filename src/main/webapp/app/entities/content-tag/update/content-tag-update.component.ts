import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';

import { IContentTag, ContentTag } from '../content-tag.model';
import { ContentTagService } from '../service/content-tag.service';
import { IContent } from 'app/entities/content/content.model';
import { ContentService } from 'app/entities/content/service/content.service';
import { ICatalog } from 'app/entities/catalog/catalog.model';
import { CatalogService } from 'app/entities/catalog/service/catalog.service';

@Component({
  selector: 'jhi-content-tag-update',
  templateUrl: './content-tag-update.component.html',
})
export class ContentTagUpdateComponent implements OnInit {
  isSaving = false;

  contentsSharedCollection: IContent[] = [];
  catalogsSharedCollection: ICatalog[] = [];

  editForm = this.fb.group({
    id: [],
    createById: [],
    createdDate: [],
    lastModifiedById: [],
    lastModifiedDate: [],
    contentId: [null, Validators.required],
    catalogId: [null, Validators.required],
  });

  constructor(
    protected contentTagService: ContentTagService,
    protected contentService: ContentService,
    protected catalogService: CatalogService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ contentTag }) => {
      if (contentTag.id === undefined) {
        const today = dayjs().startOf('day');
        contentTag.createdDate = today;
        contentTag.lastModifiedDate = today;
      }

      this.updateForm(contentTag);

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const contentTag = this.createFromForm();
    if (contentTag.id !== undefined) {
      this.subscribeToSaveResponse(this.contentTagService.update(contentTag));
    } else {
      this.subscribeToSaveResponse(this.contentTagService.create(contentTag));
    }
  }

  trackContentById(index: number, item: IContent): number {
    return item.id!;
  }

  trackCatalogById(index: number, item: ICatalog): number {
    return item.id!;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IContentTag>>): void {
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

  protected updateForm(contentTag: IContentTag): void {
    this.editForm.patchValue({
      id: contentTag.id,
      createById: contentTag.createById,
      createdDate: contentTag.createdDate ? contentTag.createdDate.format(DATE_TIME_FORMAT) : null,
      lastModifiedById: contentTag.lastModifiedById,
      lastModifiedDate: contentTag.lastModifiedDate ? contentTag.lastModifiedDate.format(DATE_TIME_FORMAT) : null,
      contentId: contentTag.contentId,
      catalogId: contentTag.catalogId,
    });

    this.contentsSharedCollection = this.contentService.addContentToCollectionIfMissing(
      this.contentsSharedCollection,
      contentTag.contentId
    );
    this.catalogsSharedCollection = this.catalogService.addCatalogToCollectionIfMissing(
      this.catalogsSharedCollection,
      contentTag.catalogId
    );
  }

  protected loadRelationshipsOptions(): void {
    this.contentService
      .query()
      .pipe(map((res: HttpResponse<IContent[]>) => res.body ?? []))
      .pipe(
        map((contents: IContent[]) => this.contentService.addContentToCollectionIfMissing(contents, this.editForm.get('contentId')!.value))
      )
      .subscribe((contents: IContent[]) => (this.contentsSharedCollection = contents));

    this.catalogService
      .query()
      .pipe(map((res: HttpResponse<ICatalog[]>) => res.body ?? []))
      .pipe(
        map((catalogs: ICatalog[]) => this.catalogService.addCatalogToCollectionIfMissing(catalogs, this.editForm.get('catalogId')!.value))
      )
      .subscribe((catalogs: ICatalog[]) => (this.catalogsSharedCollection = catalogs));
  }

  protected createFromForm(): IContentTag {
    return {
      ...new ContentTag(),
      id: this.editForm.get(['id'])!.value,
      createById: this.editForm.get(['createById'])!.value,
      createdDate: this.editForm.get(['createdDate'])!.value
        ? dayjs(this.editForm.get(['createdDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      lastModifiedById: this.editForm.get(['lastModifiedById'])!.value,
      lastModifiedDate: this.editForm.get(['lastModifiedDate'])!.value
        ? dayjs(this.editForm.get(['lastModifiedDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      contentId: this.editForm.get(['contentId'])!.value,
      catalogId: this.editForm.get(['catalogId'])!.value,
    };
  }
}
