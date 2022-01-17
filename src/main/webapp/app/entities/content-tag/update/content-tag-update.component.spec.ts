import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { ContentTagService } from '../service/content-tag.service';
import { IContentTag, ContentTag } from '../content-tag.model';
import { IContent } from 'app/entities/content/content.model';
import { ContentService } from 'app/entities/content/service/content.service';
import { ICatalog } from 'app/entities/catalog/catalog.model';
import { CatalogService } from 'app/entities/catalog/service/catalog.service';

import { ContentTagUpdateComponent } from './content-tag-update.component';

describe('ContentTag Management Update Component', () => {
  let comp: ContentTagUpdateComponent;
  let fixture: ComponentFixture<ContentTagUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let contentTagService: ContentTagService;
  let contentService: ContentService;
  let catalogService: CatalogService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [ContentTagUpdateComponent],
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
      .overrideTemplate(ContentTagUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(ContentTagUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    contentTagService = TestBed.inject(ContentTagService);
    contentService = TestBed.inject(ContentService);
    catalogService = TestBed.inject(CatalogService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Content query and add missing value', () => {
      const contentTag: IContentTag = { id: 456 };
      const contentId: IContent = { id: 57453 };
      contentTag.contentId = contentId;

      const contentCollection: IContent[] = [{ id: 11991 }];
      jest.spyOn(contentService, 'query').mockReturnValue(of(new HttpResponse({ body: contentCollection })));
      const additionalContents = [contentId];
      const expectedCollection: IContent[] = [...additionalContents, ...contentCollection];
      jest.spyOn(contentService, 'addContentToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ contentTag });
      comp.ngOnInit();

      expect(contentService.query).toHaveBeenCalled();
      expect(contentService.addContentToCollectionIfMissing).toHaveBeenCalledWith(contentCollection, ...additionalContents);
      expect(comp.contentsSharedCollection).toEqual(expectedCollection);
    });

    it('Should call Catalog query and add missing value', () => {
      const contentTag: IContentTag = { id: 456 };
      const catalogId: ICatalog = { id: 66159 };
      contentTag.catalogId = catalogId;

      const catalogCollection: ICatalog[] = [{ id: 88125 }];
      jest.spyOn(catalogService, 'query').mockReturnValue(of(new HttpResponse({ body: catalogCollection })));
      const additionalCatalogs = [catalogId];
      const expectedCollection: ICatalog[] = [...additionalCatalogs, ...catalogCollection];
      jest.spyOn(catalogService, 'addCatalogToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ contentTag });
      comp.ngOnInit();

      expect(catalogService.query).toHaveBeenCalled();
      expect(catalogService.addCatalogToCollectionIfMissing).toHaveBeenCalledWith(catalogCollection, ...additionalCatalogs);
      expect(comp.catalogsSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const contentTag: IContentTag = { id: 456 };
      const contentId: IContent = { id: 83471 };
      contentTag.contentId = contentId;
      const catalogId: ICatalog = { id: 21787 };
      contentTag.catalogId = catalogId;

      activatedRoute.data = of({ contentTag });
      comp.ngOnInit();

      expect(comp.editForm.value).toEqual(expect.objectContaining(contentTag));
      expect(comp.contentsSharedCollection).toContain(contentId);
      expect(comp.catalogsSharedCollection).toContain(catalogId);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ContentTag>>();
      const contentTag = { id: 123 };
      jest.spyOn(contentTagService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ contentTag });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: contentTag }));
      saveSubject.complete();

      // THEN
      expect(comp.previousState).toHaveBeenCalled();
      expect(contentTagService.update).toHaveBeenCalledWith(contentTag);
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ContentTag>>();
      const contentTag = new ContentTag();
      jest.spyOn(contentTagService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ contentTag });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: contentTag }));
      saveSubject.complete();

      // THEN
      expect(contentTagService.create).toHaveBeenCalledWith(contentTag);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ContentTag>>();
      const contentTag = { id: 123 };
      jest.spyOn(contentTagService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ contentTag });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(contentTagService.update).toHaveBeenCalledWith(contentTag);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Tracking relationships identifiers', () => {
    describe('trackContentById', () => {
      it('Should return tracked Content primary key', () => {
        const entity = { id: 123 };
        const trackResult = comp.trackContentById(0, entity);
        expect(trackResult).toEqual(entity.id);
      });
    });

    describe('trackCatalogById', () => {
      it('Should return tracked Catalog primary key', () => {
        const entity = { id: 123 };
        const trackResult = comp.trackCatalogById(0, entity);
        expect(trackResult).toEqual(entity.id);
      });
    });
  });
});
