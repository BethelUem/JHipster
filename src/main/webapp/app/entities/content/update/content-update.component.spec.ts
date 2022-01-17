import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { ContentService } from '../service/content.service';
import { IContent, Content } from '../content.model';
import { ICatalog } from 'app/entities/catalog/catalog.model';
import { CatalogService } from 'app/entities/catalog/service/catalog.service';

import { ContentUpdateComponent } from './content-update.component';

describe('Content Management Update Component', () => {
  let comp: ContentUpdateComponent;
  let fixture: ComponentFixture<ContentUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let contentService: ContentService;
  let catalogService: CatalogService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [ContentUpdateComponent],
      providers: [
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(ContentUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(ContentUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    contentService = TestBed.inject(ContentService);
    catalogService = TestBed.inject(CatalogService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Catalog query and add missing value', () => {
      const content: IContent = { id: 456 };
      const catContentType: ICatalog = { id: 78068 };
      content.catContentType = catContentType;
      const catTypeConduction: ICatalog = { id: 43405 };
      content.catTypeConduction = catTypeConduction;
      const catProgram: ICatalog = { id: 24211 };
      content.catProgram = catProgram;
      const catShiftRecording: ICatalog = { id: 55262 };
      content.catShiftRecording = catShiftRecording;
      const catTargetAudience: ICatalog = { id: 34491 };
      content.catTargetAudience = catTargetAudience;
      const catCountry: ICatalog = { id: 95302 };
      content.catCountry = catCountry;
      const catProductionCompany: ICatalog = { id: 62296 };
      content.catProductionCompany = catProductionCompany;
      const catArchivalCollection: ICatalog = { id: 7982 };
      content.catArchivalCollection = catArchivalCollection;
      const catOriginalLanguage: ICatalog = { id: 1515 };
      content.catOriginalLanguage = catOriginalLanguage;
      const catInterpreterLanguage: ICatalog = { id: 49063 };
      content.catInterpreterLanguage = catInterpreterLanguage;
      const catDubbingLanguage: ICatalog = { id: 90002 };
      content.catDubbingLanguage = catDubbingLanguage;
      const catSubtitleLanguage: ICatalog = { id: 19600 };
      content.catSubtitleLanguage = catSubtitleLanguage;
      const catTvCensorship: ICatalog = { id: 65989 };
      content.catTvCensorship = catTvCensorship;
      const catClassificationCategory: ICatalog = { id: 85084 };
      content.catClassificationCategory = catClassificationCategory;
      const catGenreProgram: ICatalog = { id: 92388 };
      content.catGenreProgram = catGenreProgram;
      const catFormatProgram: ICatalog = { id: 35003 };
      content.catFormatProgram = catFormatProgram;
      const catResolutionOriginal: ICatalog = { id: 7222 };
      content.catResolutionOriginal = catResolutionOriginal;
      const catResolutionEdited: ICatalog = { id: 21620 };
      content.catResolutionEdited = catResolutionEdited;
      const catCatalogingLevelOriginal: ICatalog = { id: 33049 };
      content.catCatalogingLevelOriginal = catCatalogingLevelOriginal;
      const catCatalogingLevelEdited: ICatalog = { id: 10007 };
      content.catCatalogingLevelEdited = catCatalogingLevelEdited;
      const catVideoQuality: ICatalog = { id: 96565 };
      content.catVideoQuality = catVideoQuality;
      const catAudioQuality: ICatalog = { id: 69545 };
      content.catAudioQuality = catAudioQuality;
      const catMusicalGroup: ICatalog = { id: 10249 };
      content.catMusicalGroup = catMusicalGroup;
      const catMusicalGenre: ICatalog = { id: 79071 };
      content.catMusicalGenre = catMusicalGenre;
      const catApproved: ICatalog = { id: 89068 };
      content.catApproved = catApproved;

      const catalogCollection: ICatalog[] = [{ id: 18619 }];
      jest.spyOn(catalogService, 'query').mockReturnValue(of(new HttpResponse({ body: catalogCollection })));
      const additionalCatalogs = [
        catContentType,
        catTypeConduction,
        catProgram,
        catShiftRecording,
        catTargetAudience,
        catCountry,
        catProductionCompany,
        catArchivalCollection,
        catOriginalLanguage,
        catInterpreterLanguage,
        catDubbingLanguage,
        catSubtitleLanguage,
        catTvCensorship,
        catClassificationCategory,
        catGenreProgram,
        catFormatProgram,
        catResolutionOriginal,
        catResolutionEdited,
        catCatalogingLevelOriginal,
        catCatalogingLevelEdited,
        catVideoQuality,
        catAudioQuality,
        catMusicalGroup,
        catMusicalGenre,
        catApproved,
      ];
      const expectedCollection: ICatalog[] = [...additionalCatalogs, ...catalogCollection];
      jest.spyOn(catalogService, 'addCatalogToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ content });
      comp.ngOnInit();

      expect(catalogService.query).toHaveBeenCalled();
      expect(catalogService.addCatalogToCollectionIfMissing).toHaveBeenCalledWith(catalogCollection, ...additionalCatalogs);
      expect(comp.catalogsSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const content: IContent = { id: 456 };
      const catContentType: ICatalog = { id: 95307 };
      content.catContentType = catContentType;
      const catTypeConduction: ICatalog = { id: 53329 };
      content.catTypeConduction = catTypeConduction;
      const catProgram: ICatalog = { id: 2084 };
      content.catProgram = catProgram;
      const catShiftRecording: ICatalog = { id: 15689 };
      content.catShiftRecording = catShiftRecording;
      const catTargetAudience: ICatalog = { id: 15935 };
      content.catTargetAudience = catTargetAudience;
      const catCountry: ICatalog = { id: 23452 };
      content.catCountry = catCountry;
      const catProductionCompany: ICatalog = { id: 49796 };
      content.catProductionCompany = catProductionCompany;
      const catArchivalCollection: ICatalog = { id: 55334 };
      content.catArchivalCollection = catArchivalCollection;
      const catOriginalLanguage: ICatalog = { id: 88854 };
      content.catOriginalLanguage = catOriginalLanguage;
      const catInterpreterLanguage: ICatalog = { id: 18497 };
      content.catInterpreterLanguage = catInterpreterLanguage;
      const catDubbingLanguage: ICatalog = { id: 27272 };
      content.catDubbingLanguage = catDubbingLanguage;
      const catSubtitleLanguage: ICatalog = { id: 57969 };
      content.catSubtitleLanguage = catSubtitleLanguage;
      const catTvCensorship: ICatalog = { id: 15850 };
      content.catTvCensorship = catTvCensorship;
      const catClassificationCategory: ICatalog = { id: 64475 };
      content.catClassificationCategory = catClassificationCategory;
      const catGenreProgram: ICatalog = { id: 46205 };
      content.catGenreProgram = catGenreProgram;
      const catFormatProgram: ICatalog = { id: 30686 };
      content.catFormatProgram = catFormatProgram;
      const catResolutionOriginal: ICatalog = { id: 60327 };
      content.catResolutionOriginal = catResolutionOriginal;
      const catResolutionEdited: ICatalog = { id: 35732 };
      content.catResolutionEdited = catResolutionEdited;
      const catCatalogingLevelOriginal: ICatalog = { id: 66298 };
      content.catCatalogingLevelOriginal = catCatalogingLevelOriginal;
      const catCatalogingLevelEdited: ICatalog = { id: 82861 };
      content.catCatalogingLevelEdited = catCatalogingLevelEdited;
      const catVideoQuality: ICatalog = { id: 88567 };
      content.catVideoQuality = catVideoQuality;
      const catAudioQuality: ICatalog = { id: 28004 };
      content.catAudioQuality = catAudioQuality;
      const catMusicalGroup: ICatalog = { id: 26860 };
      content.catMusicalGroup = catMusicalGroup;
      const catMusicalGenre: ICatalog = { id: 70229 };
      content.catMusicalGenre = catMusicalGenre;
      const catApproved: ICatalog = { id: 53206 };
      content.catApproved = catApproved;

      activatedRoute.data = of({ content });
      comp.ngOnInit();

      expect(comp.editForm.value).toEqual(expect.objectContaining(content));
      expect(comp.catalogsSharedCollection).toContain(catContentType);
      expect(comp.catalogsSharedCollection).toContain(catTypeConduction);
      expect(comp.catalogsSharedCollection).toContain(catProgram);
      expect(comp.catalogsSharedCollection).toContain(catShiftRecording);
      expect(comp.catalogsSharedCollection).toContain(catTargetAudience);
      expect(comp.catalogsSharedCollection).toContain(catCountry);
      expect(comp.catalogsSharedCollection).toContain(catProductionCompany);
      expect(comp.catalogsSharedCollection).toContain(catArchivalCollection);
      expect(comp.catalogsSharedCollection).toContain(catOriginalLanguage);
      expect(comp.catalogsSharedCollection).toContain(catInterpreterLanguage);
      expect(comp.catalogsSharedCollection).toContain(catDubbingLanguage);
      expect(comp.catalogsSharedCollection).toContain(catSubtitleLanguage);
      expect(comp.catalogsSharedCollection).toContain(catTvCensorship);
      expect(comp.catalogsSharedCollection).toContain(catClassificationCategory);
      expect(comp.catalogsSharedCollection).toContain(catGenreProgram);
      expect(comp.catalogsSharedCollection).toContain(catFormatProgram);
      expect(comp.catalogsSharedCollection).toContain(catResolutionOriginal);
      expect(comp.catalogsSharedCollection).toContain(catResolutionEdited);
      expect(comp.catalogsSharedCollection).toContain(catCatalogingLevelOriginal);
      expect(comp.catalogsSharedCollection).toContain(catCatalogingLevelEdited);
      expect(comp.catalogsSharedCollection).toContain(catVideoQuality);
      expect(comp.catalogsSharedCollection).toContain(catAudioQuality);
      expect(comp.catalogsSharedCollection).toContain(catMusicalGroup);
      expect(comp.catalogsSharedCollection).toContain(catMusicalGenre);
      expect(comp.catalogsSharedCollection).toContain(catApproved);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<Content>>();
      const content = { id: 123 };
      jest.spyOn(contentService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ content });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: content }));
      saveSubject.complete();

      // THEN
      expect(comp.previousState).toHaveBeenCalled();
      expect(contentService.update).toHaveBeenCalledWith(content);
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<Content>>();
      const content = new Content();
      jest.spyOn(contentService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ content });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: content }));
      saveSubject.complete();

      // THEN
      expect(contentService.create).toHaveBeenCalledWith(content);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<Content>>();
      const content = { id: 123 };
      jest.spyOn(contentService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ content });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(contentService.update).toHaveBeenCalledWith(content);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Tracking relationships identifiers', () => {
    describe('trackCatalogById', () => {
      it('Should return tracked Catalog primary key', () => {
        const entity = { id: 123 };
        const trackResult = comp.trackCatalogById(0, entity);
        expect(trackResult).toEqual(entity.id);
      });
    });
  });
});
