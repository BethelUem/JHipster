import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';

import { IContent, Content } from '../content.model';
import { ContentService } from '../service/content.service';
import { ICatalog } from 'app/entities/catalog/catalog.model';
import { CatalogService } from 'app/entities/catalog/service/catalog.service';

@Component({
  selector: 'jhi-content-update',
  templateUrl: './content-update.component.html',
})
export class ContentUpdateComponent implements OnInit {
  isSaving = false;

  catalogsSharedCollection: ICatalog[] = [];

  editForm = this.fb.group({
    id: [],
    recordingDate: [],
    editionDate: [],
    signatureOriginal: [],
    signatureEdited: [],
    eventReason: [],
    eventSlogan: [],
    nameConduction: [],
    positionConduction: [],
    specialtiesPanel: [],
    namePanel: [],
    titleSubject: [],
    biblicalPassage: [],
    synopsisDescription: [],
    storyline: [],
    mediaContent: [],
    durationOriginal: [],
    durationEdited: [],
    scenography: [],
    location: [],
    city: [],
    departmentState: [],
    producer: [],
    productionAssistant: [],
    operatorVTRPlayOut: [],
    productionCredits: [],
    castActors: [],
    archiveStatus: [],
    interpreterTranslator: [],
    dubbing: [],
    subtitleCC: [],
    cataloguerOriginal: [],
    cataloguerEdited: [],
    postproductionEditor: [],
    operatorIngestion: [],
    serviceOTT: [],
    archivadoLTOOriginal: [],
    archivadoLTOEdited: [],
    observations: [],
    rightsManagement: [],
    cataloguerProduction: [],
    cataloguerIngest: [],
    cataloguerMaster: [],
    urlEdition: [],
    urlOriginal: [],
    createById: [],
    createdDate: [],
    lastModifiedById: [],
    lastModifiedDate: [],
    catContentType: [],
    catTypeConduction: [],
    catProgram: [],
    catShiftRecording: [],
    catTargetAudience: [],
    catCountry: [],
    catProductionCompany: [],
    catArchivalCollection: [],
    catOriginalLanguage: [],
    catInterpreterLanguage: [],
    catDubbingLanguage: [],
    catSubtitleLanguage: [],
    catTvCensorship: [],
    catClassificationCategory: [],
    catGenreProgram: [],
    catFormatProgram: [],
    catResolutionOriginal: [],
    catResolutionEdited: [],
    catCatalogingLevelOriginal: [],
    catCatalogingLevelEdited: [],
    catVideoQuality: [],
    catAudioQuality: [],
    catMusicalGroup: [],
    catMusicalGenre: [],
    catApproved: [],
  });

  constructor(
    protected contentService: ContentService,
    protected catalogService: CatalogService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ content }) => {
      if (content.id === undefined) {
        const today = dayjs().startOf('day');
        content.recordingDate = today;
        content.editionDate = today;
        content.createdDate = today;
        content.lastModifiedDate = today;
      }

      this.updateForm(content);

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const content = this.createFromForm();
    if (content.id !== undefined) {
      this.subscribeToSaveResponse(this.contentService.update(content));
    } else {
      this.subscribeToSaveResponse(this.contentService.create(content));
    }
  }

  trackCatalogById(index: number, item: ICatalog): number {
    return item.id!;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IContent>>): void {
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

  protected updateForm(content: IContent): void {
    this.editForm.patchValue({
      id: content.id,
      recordingDate: content.recordingDate ? content.recordingDate.format(DATE_TIME_FORMAT) : null,
      editionDate: content.editionDate ? content.editionDate.format(DATE_TIME_FORMAT) : null,
      signatureOriginal: content.signatureOriginal,
      signatureEdited: content.signatureEdited,
      eventReason: content.eventReason,
      eventSlogan: content.eventSlogan,
      nameConduction: content.nameConduction,
      positionConduction: content.positionConduction,
      specialtiesPanel: content.specialtiesPanel,
      namePanel: content.namePanel,
      titleSubject: content.titleSubject,
      biblicalPassage: content.biblicalPassage,
      synopsisDescription: content.synopsisDescription,
      storyline: content.storyline,
      mediaContent: content.mediaContent,
      durationOriginal: content.durationOriginal,
      durationEdited: content.durationEdited,
      scenography: content.scenography,
      location: content.location,
      city: content.city,
      departmentState: content.departmentState,
      producer: content.producer,
      productionAssistant: content.productionAssistant,
      operatorVTRPlayOut: content.operatorVTRPlayOut,
      productionCredits: content.productionCredits,
      castActors: content.castActors,
      archiveStatus: content.archiveStatus,
      interpreterTranslator: content.interpreterTranslator,
      dubbing: content.dubbing,
      subtitleCC: content.subtitleCC,
      cataloguerOriginal: content.cataloguerOriginal,
      cataloguerEdited: content.cataloguerEdited,
      postproductionEditor: content.postproductionEditor,
      operatorIngestion: content.operatorIngestion,
      serviceOTT: content.serviceOTT,
      archivadoLTOOriginal: content.archivadoLTOOriginal,
      archivadoLTOEdited: content.archivadoLTOEdited,
      observations: content.observations,
      rightsManagement: content.rightsManagement,
      cataloguerProduction: content.cataloguerProduction,
      cataloguerIngest: content.cataloguerIngest,
      cataloguerMaster: content.cataloguerMaster,
      urlEdition: content.urlEdition,
      urlOriginal: content.urlOriginal,
      createById: content.createById,
      createdDate: content.createdDate ? content.createdDate.format(DATE_TIME_FORMAT) : null,
      lastModifiedById: content.lastModifiedById,
      lastModifiedDate: content.lastModifiedDate ? content.lastModifiedDate.format(DATE_TIME_FORMAT) : null,
      catContentType: content.catContentType,
      catTypeConduction: content.catTypeConduction,
      catProgram: content.catProgram,
      catShiftRecording: content.catShiftRecording,
      catTargetAudience: content.catTargetAudience,
      catCountry: content.catCountry,
      catProductionCompany: content.catProductionCompany,
      catArchivalCollection: content.catArchivalCollection,
      catOriginalLanguage: content.catOriginalLanguage,
      catInterpreterLanguage: content.catInterpreterLanguage,
      catDubbingLanguage: content.catDubbingLanguage,
      catSubtitleLanguage: content.catSubtitleLanguage,
      catTvCensorship: content.catTvCensorship,
      catClassificationCategory: content.catClassificationCategory,
      catGenreProgram: content.catGenreProgram,
      catFormatProgram: content.catFormatProgram,
      catResolutionOriginal: content.catResolutionOriginal,
      catResolutionEdited: content.catResolutionEdited,
      catCatalogingLevelOriginal: content.catCatalogingLevelOriginal,
      catCatalogingLevelEdited: content.catCatalogingLevelEdited,
      catVideoQuality: content.catVideoQuality,
      catAudioQuality: content.catAudioQuality,
      catMusicalGroup: content.catMusicalGroup,
      catMusicalGenre: content.catMusicalGenre,
      catApproved: content.catApproved,
    });

    this.catalogsSharedCollection = this.catalogService.addCatalogToCollectionIfMissing(
      this.catalogsSharedCollection,
      content.catContentType,
      content.catTypeConduction,
      content.catProgram,
      content.catShiftRecording,
      content.catTargetAudience,
      content.catCountry,
      content.catProductionCompany,
      content.catArchivalCollection,
      content.catOriginalLanguage,
      content.catInterpreterLanguage,
      content.catDubbingLanguage,
      content.catSubtitleLanguage,
      content.catTvCensorship,
      content.catClassificationCategory,
      content.catGenreProgram,
      content.catFormatProgram,
      content.catResolutionOriginal,
      content.catResolutionEdited,
      content.catCatalogingLevelOriginal,
      content.catCatalogingLevelEdited,
      content.catVideoQuality,
      content.catAudioQuality,
      content.catMusicalGroup,
      content.catMusicalGenre,
      content.catApproved
    );
  }

  protected loadRelationshipsOptions(): void {
    this.catalogService
      .query()
      .pipe(map((res: HttpResponse<ICatalog[]>) => res.body ?? []))
      .pipe(
        map((catalogs: ICatalog[]) =>
          this.catalogService.addCatalogToCollectionIfMissing(
            catalogs,
            this.editForm.get('catContentType')!.value,
            this.editForm.get('catTypeConduction')!.value,
            this.editForm.get('catProgram')!.value,
            this.editForm.get('catShiftRecording')!.value,
            this.editForm.get('catTargetAudience')!.value,
            this.editForm.get('catCountry')!.value,
            this.editForm.get('catProductionCompany')!.value,
            this.editForm.get('catArchivalCollection')!.value,
            this.editForm.get('catOriginalLanguage')!.value,
            this.editForm.get('catInterpreterLanguage')!.value,
            this.editForm.get('catDubbingLanguage')!.value,
            this.editForm.get('catSubtitleLanguage')!.value,
            this.editForm.get('catTvCensorship')!.value,
            this.editForm.get('catClassificationCategory')!.value,
            this.editForm.get('catGenreProgram')!.value,
            this.editForm.get('catFormatProgram')!.value,
            this.editForm.get('catResolutionOriginal')!.value,
            this.editForm.get('catResolutionEdited')!.value,
            this.editForm.get('catCatalogingLevelOriginal')!.value,
            this.editForm.get('catCatalogingLevelEdited')!.value,
            this.editForm.get('catVideoQuality')!.value,
            this.editForm.get('catAudioQuality')!.value,
            this.editForm.get('catMusicalGroup')!.value,
            this.editForm.get('catMusicalGenre')!.value,
            this.editForm.get('catApproved')!.value
          )
        )
      )
      .subscribe((catalogs: ICatalog[]) => (this.catalogsSharedCollection = catalogs));
  }

  protected createFromForm(): IContent {
    return {
      ...new Content(),
      id: this.editForm.get(['id'])!.value,
      recordingDate: this.editForm.get(['recordingDate'])!.value
        ? dayjs(this.editForm.get(['recordingDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      editionDate: this.editForm.get(['editionDate'])!.value
        ? dayjs(this.editForm.get(['editionDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      signatureOriginal: this.editForm.get(['signatureOriginal'])!.value,
      signatureEdited: this.editForm.get(['signatureEdited'])!.value,
      eventReason: this.editForm.get(['eventReason'])!.value,
      eventSlogan: this.editForm.get(['eventSlogan'])!.value,
      nameConduction: this.editForm.get(['nameConduction'])!.value,
      positionConduction: this.editForm.get(['positionConduction'])!.value,
      specialtiesPanel: this.editForm.get(['specialtiesPanel'])!.value,
      namePanel: this.editForm.get(['namePanel'])!.value,
      titleSubject: this.editForm.get(['titleSubject'])!.value,
      biblicalPassage: this.editForm.get(['biblicalPassage'])!.value,
      synopsisDescription: this.editForm.get(['synopsisDescription'])!.value,
      storyline: this.editForm.get(['storyline'])!.value,
      mediaContent: this.editForm.get(['mediaContent'])!.value,
      durationOriginal: this.editForm.get(['durationOriginal'])!.value,
      durationEdited: this.editForm.get(['durationEdited'])!.value,
      scenography: this.editForm.get(['scenography'])!.value,
      location: this.editForm.get(['location'])!.value,
      city: this.editForm.get(['city'])!.value,
      departmentState: this.editForm.get(['departmentState'])!.value,
      producer: this.editForm.get(['producer'])!.value,
      productionAssistant: this.editForm.get(['productionAssistant'])!.value,
      operatorVTRPlayOut: this.editForm.get(['operatorVTRPlayOut'])!.value,
      productionCredits: this.editForm.get(['productionCredits'])!.value,
      castActors: this.editForm.get(['castActors'])!.value,
      archiveStatus: this.editForm.get(['archiveStatus'])!.value,
      interpreterTranslator: this.editForm.get(['interpreterTranslator'])!.value,
      dubbing: this.editForm.get(['dubbing'])!.value,
      subtitleCC: this.editForm.get(['subtitleCC'])!.value,
      cataloguerOriginal: this.editForm.get(['cataloguerOriginal'])!.value,
      cataloguerEdited: this.editForm.get(['cataloguerEdited'])!.value,
      postproductionEditor: this.editForm.get(['postproductionEditor'])!.value,
      operatorIngestion: this.editForm.get(['operatorIngestion'])!.value,
      serviceOTT: this.editForm.get(['serviceOTT'])!.value,
      archivadoLTOOriginal: this.editForm.get(['archivadoLTOOriginal'])!.value,
      archivadoLTOEdited: this.editForm.get(['archivadoLTOEdited'])!.value,
      observations: this.editForm.get(['observations'])!.value,
      rightsManagement: this.editForm.get(['rightsManagement'])!.value,
      cataloguerProduction: this.editForm.get(['cataloguerProduction'])!.value,
      cataloguerIngest: this.editForm.get(['cataloguerIngest'])!.value,
      cataloguerMaster: this.editForm.get(['cataloguerMaster'])!.value,
      urlEdition: this.editForm.get(['urlEdition'])!.value,
      urlOriginal: this.editForm.get(['urlOriginal'])!.value,
      createById: this.editForm.get(['createById'])!.value,
      createdDate: this.editForm.get(['createdDate'])!.value
        ? dayjs(this.editForm.get(['createdDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      lastModifiedById: this.editForm.get(['lastModifiedById'])!.value,
      lastModifiedDate: this.editForm.get(['lastModifiedDate'])!.value
        ? dayjs(this.editForm.get(['lastModifiedDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      catContentType: this.editForm.get(['catContentType'])!.value,
      catTypeConduction: this.editForm.get(['catTypeConduction'])!.value,
      catProgram: this.editForm.get(['catProgram'])!.value,
      catShiftRecording: this.editForm.get(['catShiftRecording'])!.value,
      catTargetAudience: this.editForm.get(['catTargetAudience'])!.value,
      catCountry: this.editForm.get(['catCountry'])!.value,
      catProductionCompany: this.editForm.get(['catProductionCompany'])!.value,
      catArchivalCollection: this.editForm.get(['catArchivalCollection'])!.value,
      catOriginalLanguage: this.editForm.get(['catOriginalLanguage'])!.value,
      catInterpreterLanguage: this.editForm.get(['catInterpreterLanguage'])!.value,
      catDubbingLanguage: this.editForm.get(['catDubbingLanguage'])!.value,
      catSubtitleLanguage: this.editForm.get(['catSubtitleLanguage'])!.value,
      catTvCensorship: this.editForm.get(['catTvCensorship'])!.value,
      catClassificationCategory: this.editForm.get(['catClassificationCategory'])!.value,
      catGenreProgram: this.editForm.get(['catGenreProgram'])!.value,
      catFormatProgram: this.editForm.get(['catFormatProgram'])!.value,
      catResolutionOriginal: this.editForm.get(['catResolutionOriginal'])!.value,
      catResolutionEdited: this.editForm.get(['catResolutionEdited'])!.value,
      catCatalogingLevelOriginal: this.editForm.get(['catCatalogingLevelOriginal'])!.value,
      catCatalogingLevelEdited: this.editForm.get(['catCatalogingLevelEdited'])!.value,
      catVideoQuality: this.editForm.get(['catVideoQuality'])!.value,
      catAudioQuality: this.editForm.get(['catAudioQuality'])!.value,
      catMusicalGroup: this.editForm.get(['catMusicalGroup'])!.value,
      catMusicalGenre: this.editForm.get(['catMusicalGenre'])!.value,
      catApproved: this.editForm.get(['catApproved'])!.value,
    };
  }
}
